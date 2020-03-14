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

package cl.ucn.disc.dsm.pag.news.services.gnews;

import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import cl.ucn.disc.dsm.pag.news.model.NewsArticleAdapter;
import cl.ucn.disc.dsm.pag.news.services.NewsService;
import cl.ucn.disc.dsm.pag.news.services.newsapi.NewsApiNewsService.NewsApiException;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class GnewsNewsService implements NewsService {

  // Adapter
  private static final NewsArticleAdapter<Article> adapter =
      new NewsArticleAdapter<Article>(new GnewsArticleAdapter());
  // Connection to the API via retrofit
  private final Gnews gnews;

  /**
   * Constructor.
   */
  public GnewsNewsService() {
    this.gnews =
        new Retrofit.Builder()
            .baseUrl(Gnews.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .validateEagerly(true)
            .build()
            .create(Gnews.class);
  }

  private static List<NewsArticle> getNewsFromCall(final Call<GnewsResult> call) {
    try {
      final Response<GnewsResult> response = call.execute();
      // Error in response
      if (!response.isSuccessful()) {
        throw new GnewsException(
            "Can't get a NewsResult " + response.code(), new HttpException(response));
      }
      final GnewsResult result = response.body();
      if (result.articles == null) {
        // No articles
        throw new NewsApiException("No articles in GnewsResult, result.articles was null");
      }

      // Adapt gnews/Article to model/NewsArticle
      return adapter.transform(result.articles);
    } catch (IOException ex) {
      throw new GnewsException("Can't get a NewsResult", ex);
    }
  }

  @Override
  public List<NewsArticle> getNews(int pageSize) {
    final Call<GnewsResult> call = this.gnews.getTopHeadlines("es", pageSize);
    return getNewsFromCall(call);
  }

  /** Gnews interface. Handles the access to the API endpoints */
  interface Gnews {
    final String BASE_URL = "https://gnews.io/api/v3/";
    final String API_KEY = "0d194e505de173cb271edc62b375c329";

    // @Headers({"X-Api-Key: " + API_KEY})
    @GET("search?q=coronavirus&country=cl&&token=" + API_KEY)
    Call<GnewsResult> getTopHeadlines(
        @Query("lang") final String lang, @Query("max") final int max);
  }

  /** Exception for Gnews Api. */
  public static final class GnewsException extends RuntimeException {

    public GnewsException(String message) {
      super(message);
    }

    public GnewsException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
