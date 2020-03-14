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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsArticleAdapter<T> {

  public static final Logger LOG = LoggerFactory.getLogger(NewsArticleAdapter.class);
  // Adapter
  private final NewsArticleTransformer<T> newsArticleTransformer;

  // Constructor
  public NewsArticleAdapter(NewsArticleTransformer<T> newsArticleTransformer) {
    Objects.requireNonNull(newsArticleTransformer, "NewsArticleTransformer can't be null.");
    this.newsArticleTransformer = newsArticleTransformer;
  }

  /**
   * Transforms a collection on a List of NewsArticle.
   *
   * @param collection The collection to be transformed
   * @return A list of NewsArticle
   */
  public List<NewsArticle> transform(final Collection<T> collection) {

    Objects.requireNonNull(collection, "Collection can't be null");

    final List<NewsArticle> news = new ArrayList<NewsArticle>(collection.size());

    for (final T t : collection) {

      try {
        news.add(newsArticleTransformer.transform(t));
      } catch (NewsArticleTransformerException e) {

        LOG.debug("Unable to transform article");
      }
    }

    return news;
  }

  /**
   * A public interface to be implemented for each T adapter.
   *
   * @param <T> A object to be transformed.
   */
  public interface NewsArticleTransformer<T> {
    NewsArticle transform(T t);
  }

  /** Runtime exception for NewsArticleTransformer. */
  public static final class NewsArticleTransformerException extends RuntimeException {

    public NewsArticleTransformerException(String message) {
      super(message);
    }

    public NewsArticleTransformerException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
