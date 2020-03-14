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
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Test NewsService interface */
public class NewsServiceTest {

  /**
   * Test service interface using mocked implementation.
   *
   * @param pageSize Number of articles to be requested.
   */
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0})
  public void testGetNews(int pageSize) {

    NewsService newsService = new MockupNewsService();

    // News list
    List<NewsArticle> news = newsService.getNews(pageSize);

    Assertions.assertNotNull(news, "Expected not null");
    Assertions.assertEquals(news.size(), pageSize, "Wrong size");
  }
}
