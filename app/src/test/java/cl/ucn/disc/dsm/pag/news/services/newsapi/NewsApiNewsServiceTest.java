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
import cl.ucn.disc.dsm.pag.news.services.NewsService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsApiNewsServiceTest {

  // Logger
  Logger LOG = LoggerFactory.getLogger(NewsApiNewsServiceTest.class);

  /**
   * Test if the NewsApi service is receiving news from the REST API.
   */
  @Test
  void testGetNewsFromNewsApi() {

    final int size = 20;
    NewsService newsService = new NewsApiNewsService();
    List<NewsArticle> news = newsService.getNews(size);

    Assertions.assertNotNull(news, "List of news was NULL");
    Assertions.assertEquals(
        news.size(),
        size,
        "Number of news requested is different that the number of news received");

    // TODO: Log the news
    /*for (final NewsArticle article : news){
      LOG.debug(String.valueOf(article));
    }*/
  }
}
