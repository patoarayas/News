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

/** Service interface. */
public interface NewsService {

  /**
   * Get the news from the back-end.
   *
   * @param pageSize How many news should be returned.
   * @return a List of news.
   */
  List<NewsArticle> getNews(final int pageSize);
}
