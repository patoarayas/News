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

package cl.ucn.disc.dsm.pag.news.model;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/** Domain class, represents a news article. */
public class NewsArticle {

  public static final ZoneId timezone = ZoneId.of("-3");
  private final Long id;
  private final String title;
  private final String source;
  private final String author;
  private final String articleUrl;
  private final String imgUrl;
  private final String summary;
  private final String content;
  private final ZonedDateTime date;

  /**
   * Constructor.
   *
   * @param builder The NewsArticleBuilder.
   */
  public NewsArticle(NewsArticleBuilder builder) {

    this.id = builder.getId();
    this.title = builder.getTitle();
    this.source = builder.getSource();
    this.author = builder.getAuthor();
    this.articleUrl = builder.getArticleUrl();
    this.imgUrl = builder.getImgUrl();
    this.summary = builder.getSummary();
    this.content = builder.getContent();
    this.date = builder.getDate();
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getSource() {
    return source;
  }

  public String getAuthor() {
    return author;
  }

  public String getArticleUrl() {
    return articleUrl;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public String getSummary() {
    return summary;
  }

  public String getContent() {
    return content;
  }

  public ZonedDateTime getDate() {
    return date;
  }
}
