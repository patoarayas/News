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

package cl.ucn.disc.dsm.pag.news;

import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.bp.ZonedDateTime;

/** Test of {@link cl.ucn.disc.dsm.pag.news.model.NewsArticle} */
public class NewsArticleTest {

  /** Test whether the constructor is working. */
  @Test
  public void constructorTest() {
    // values
    final Long id = 1L;
    final String title = "A title";
    final String source = "The source";
    final String author = "The author";
    final String articleUrl = "The URL";
    final String imgUrl = "The URL of an image";
    final String summary = "A summary";
    final String content = "The content";
    final ZonedDateTime date = ZonedDateTime.now(NewsArticle.timezone);

    // NewsArticle instance
    final NewsArticle article =
        new NewsArticle(id, title, source, author, articleUrl, imgUrl, summary, content, date);

    // Assertions
    Assertions.assertEquals(id, article.getId());
    Assertions.assertEquals(title, article.getTitle());
    Assertions.assertEquals(source, article.getSource());
    Assertions.assertEquals(author, article.getAuthor());
    Assertions.assertEquals(articleUrl, article.getArticleUrl());
    Assertions.assertEquals(imgUrl, article.getImgUrl());
    Assertions.assertEquals(summary, article.getSummary());
    Assertions.assertEquals(content, article.getContent());
    Assertions.assertEquals(date, article.getDate());
  }
}
