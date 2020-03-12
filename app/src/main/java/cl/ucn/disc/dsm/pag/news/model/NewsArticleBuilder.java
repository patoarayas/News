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
 * Builder class for NewsArticle.
 * To manage the complexity of making a NewsArticle Object
 */
public class NewsArticleBuilder {

  public static final ZoneId timezone = ZoneId.of("-3");
  private final Long id;
  private final String title;
  private final String summary;
  private  String source;
  private  String author;
  private  String articleUrl;
  private  String imgUrl;
  private  String content;
  private  ZonedDateTime date;

  /**
   * Constructor
   * @param id Internal id to the article
   * @param title Article's title.
   * @param summary Articles's summary.
   */
  public NewsArticleBuilder(Long id, String title, String summary, ZonedDateTime date) {
    // Minimum fields
    this.id = id;
    this.title = title;
    this.summary = summary;
    this.date = date;

    // Default fields
    this.source = "No source";
    this.author = "Anonymous";
    this.articleUrl= "No URL to article";
    this.imgUrl = "No URL to image";
    this.content = "No more content";
    this.date = null;
  }

  /**
   * Builder method.
   * @return A well formed NewsArticle instance.
   */
  public NewsArticle build(){
    return new NewsArticle(this);
  }

  /**
   * Add a source to the NewsArticle to be created.
   * @param source A source to be added
   * @return The Builder
   */
  public NewsArticleBuilder withSource(String source){
    if(source != null && !source.equals("")){
      this.source = source;
    }
    return this;
  }

  /**
   * Add an author to the NewsArticle to be created.
   * @param author Name of the author to be added
   * @return The Builder
   */
  public NewsArticleBuilder withAuthor(String author){
    if(author != null && !author.equals("")){
      this.author = author;
    }
    return this;
  }

  /**
   * Add an URL to the NewsArticle to be created
   * @param articleUrl An http link to the article.
   * @return The Builder
   */
  public NewsArticleBuilder withArticleUrl(String articleUrl){
    if(articleUrl!= null && !articleUrl.equals("")){
      this.author = author;
    }
    return this;
  }

  /**
   * Add an URL to an image related to the article.
   * @param imgUrl An http link to the image.
   * @return The Builder
   */
  public NewsArticleBuilder withImageUrl(String imgUrl){
    if(imgUrl != null && !imgUrl.equals("")){
      this.imgUrl = imgUrl;
    }
    return this;
  }

  /**
   * Add the body content to the NewsArticle
   * @param content The content of the article.
   * @return The Builder
   */
  public NewsArticleBuilder withContent(String content){
    if(content != null && !content.equals("") ){
      this.content = content;
    }
    return this;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getSummary() {
    return summary;
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

  public String getContent() {
    return content;
  }

  public ZonedDateTime getDate() {
    return date;
  }
}
