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

/**
 * Domain class, represents a news article.
 */
public class NewsArticle {

  private final Long id;
  private final String title;
  private final String source;
  private final String author;
  private final String articleUrl;
  private final String imgUrl;
  private final String summary;
  private final String content;
  private final ZonedDateTime date;
  public static final ZoneId timezone = ZoneId.of("-3");

  /**
   * Constructor
   * @param id NewsArticle ID
   * @param title The article title
   * @param source Origin of the article
   * @param author Name of the writer or creator
   * @param articleUrl URL to the article
   * @param imgUrl URL to an image related to the article
   * @param summary A description of the content
   * @param content The content of the article
   * @param date The date of publication.
   */
  public NewsArticle(Long id, String title, String source, String author, String articleUrl,
      String imgUrl, String summary, String content, ZonedDateTime date) {

    this.id = id;
    this.title = title;
    this.source = source;
    this.author = author;
    this.articleUrl = articleUrl;
    this.imgUrl = imgUrl;
    this.summary = summary;
    this.content = content;
    this.date = date;
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
