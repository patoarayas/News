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

package cl.ucn.disc.dsm.pag.news.services;

import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import cl.ucn.disc.dsm.pag.news.model.NewsArticleBuilder;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.ZonedDateTime;

/**
 * Mocks a news resource. implements NewsService
 */
public class MockupNewsService implements NewsService{

  /**
   * Constructor
   */
  public MockupNewsService() {
  }

  /**
   * Creates a List of news.
   * @param pageSize How many news should be returned.
   * @return A List of news articles.
   */
  @Override
  public List<NewsArticle> getNews(int pageSize) {

    List<NewsArticle> news = new ArrayList<NewsArticle>();

    for(int i = 0; i < pageSize; i++){
      NewsArticle art = new NewsArticleBuilder(i*1L,"MockTitle","MockSummary",ZonedDateTime.now()).build();
      news.add(art);
    }

    return news;

  }
}
