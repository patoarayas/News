/*
 *
 * Copyright (C) 2020  Patricio Araya González
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cl.ucn.disc.dsm.pag.news.services.newsapi;

import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import cl.ucn.disc.dsm.pag.news.model.NewsArticleAdapter;
import cl.ucn.disc.dsm.pag.news.services.NewsService;
import java.io.IOException;
import java.util.List;
import javax.xml.transform.Transformer;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class NewsApiNewsService implements NewsService {

  // Connection to the API via Retrofit
  private final NewsApi newsApi;
  // Adapter
  private static final NewsArticleAdapter<Article> adapter =
      new NewsArticleAdapter<Article>(new NewsApiArticleAdapter());

  // Constructor
  public NewsApiNewsService() {

    this.newsApi =
        new Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .validateEagerly(true)
            .build()
            .create(NewsApi.class);
  }

  private static List<NewsArticle> getNewsFromCall(final Call<NewsApiResult> call) {
    try {
      final Response<NewsApiResult> response = call.execute();

      // Error in response
      if (!response.isSuccessful()) {
        throw new NewsApiException(
            "Can't get a NewsResult " + response.code(), new HttpException(response));
      }
      final NewsApiResult result = response.body();
      if (result.articles == null) {
        // No articles
        throw new NewsApiException("No articles in NewsApiResult, result.articles was null");
      }

      // Adapt newsapi/Article to model/NewsArticle
      return adapter.transform(result.articles);
    } catch (IOException ex) {
      throw new NewsApiException("Can't get a NewsResult", ex);
    }
  }

  // TODO: implement category change or getEverthing()
  @Override
  public List<NewsArticle> getNews(int pageSize) {
    final Call<NewsApiResult> call = this.newsApi.getTopHeadlines("all",pageSize);
    return getNewsFromCall(call);
  }

  /** News Api interface. Handles the access to the newsApi endpoints. Stores the API key */
  interface NewsApi {
    final String BASE_URL = "https://newsapi.org/v2/";
    final String API_KEY = "e334a941d3394d5099c7a73994981601";

    /**
     * Makes a request to NewsAPI
     *
     * @param category The category requested.
     * @param pageSize The number of news requested
     * @return A NewsApiResult (A list of newsApi Articles)
     */
    @Headers({"X-Api-Key: " + API_KEY})
    @GET("top-headlines")
    Call<NewsApiResult> getTopHeadlines(
        @Query("category") final String category, @Query("pageSize") final int pageSize);
  }

  /** Exception for NewsAPIService. */
  public static final class NewsApiException extends RuntimeException {

    public NewsApiException(final String message) {
      super(message);
    }

    public NewsApiException(final String message, final Throwable cause) {
      super(message, cause);
    }
  }
}
