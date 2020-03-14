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
import cl.ucn.disc.dsm.pag.news.model.NewsArticleBuilder;
import cl.ucn.disc.dsm.pag.news.services.gnews.GnewsNewsService.Gnews;
import net.openhft.hashing.LongHashFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeParseException;

public class GnewsArticleAdapter implements NewsArticleAdapter.NewsArticleTransformer<Article> {

  public final static Logger LOG = LoggerFactory.getLogger(GnewsArticleAdapter.class);
  /**
   * Parse Article's date field into a valid ZonedDateTime
   *
   * @param date Date to be parsed.
   * @return A valid ZonedDateTime
   */
  private static ZonedDateTime parseZonedDateTime(final String date) {
    if (date == null) {
      throw new NewsArticleAdapter.NewsArticleTransformerException("Article date was null");
    }
    try {
      // Try to parse date
      return ZonedDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
    } catch (DateTimeParseException ex) {

      throw new NewsArticleAdapter.NewsArticleTransformerException(

          "Unable to parse date of article: " + date, ex);
    }
  }

  /**
   * Transform an article from GNews into a NewsArticle.
   * @param article The article to be transformed
   * @return A NewsArticle
   */
  @Override
  public NewsArticle transform(Article article) {

    // Null article
    if (article == null) {
      throw new NewsArticleAdapter.NewsArticleTransformerException("Article was null");
    }
    // No title
    if (article.title == null) {
      throw new NewsArticleAdapter.NewsArticleTransformerException("Article with no title");
    }
    // No summary or description
    if (article.description == null) {
      throw new NewsArticleAdapter.NewsArticleTransformerException("article with no description");
    }

    // ID
    final Long id = LongHashFunction.xx().hashChars(article.title + article.source.name);

    // Parsed publishedAt
    final ZonedDateTime publishedAt =
        parseZonedDateTime(article.publishedAt).withZoneSameInstant(NewsArticle.timezone);

    String description = article.description.equals("") ? "See more at: "+article.url : article.description;
    NewsArticleBuilder builder =
        new NewsArticleBuilder(id, article.title, description, publishedAt)
            .withSource(article.source.name)
            .withAuthor(article.source.name+" team.")
            .withArticleUrl(article.url)
            .withImageUrl(article.image);

    return builder.build();
  }
}
