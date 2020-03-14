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

package cl.ucn.disc.dsm.pag.news.activities;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.news.databinding.NewsArticleRowBinding;
import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import cl.ucn.disc.dsm.pag.news.services.NewsService;
import cl.ucn.disc.dsm.pag.news.services.gnews.GnewsNewsService;
import cl.ucn.disc.dsm.pag.news.services.newsapi.NewsApiNewsService;
import java.util.ArrayList;
import java.util.List;

public class NewsArticleViewHolderAdapter extends RecyclerView.Adapter<NewsArticleViewHolder> {

  private List<NewsArticle> news;


  /**
   * Constructor
   */
  public NewsArticleViewHolderAdapter() {
    this.news = new ArrayList<NewsArticle>();
  }

  public void setNews(final List<NewsArticle> news){
    // Update the list of news
    this.news = news;
    this.notifyDataSetChanged();
  }

  @NonNull
  @Override
  public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    return new NewsArticleViewHolder(NewsArticleRowBinding.inflate(layoutInflater, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NewsArticleViewHolder holder, int position) {
    holder.bind(this.news.get(position));
  }

  @Override
  public int getItemCount() {
    return this.news.size();
  }
}
