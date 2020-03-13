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

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.news.R;
import cl.ucn.disc.dsm.pag.news.databinding.NewsArticleRowBinding;
import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.DateTimeUtils;

public class NewsArticleViewHolder extends RecyclerView.ViewHolder {



  private final NewsArticleRowBinding binding;
  // PrettyTime date formatter
  private static final PrettyTime PRETTY_TIME = new PrettyTime();

  public NewsArticleViewHolder(NewsArticleRowBinding rowNewsArticleBinding) {
    super(rowNewsArticleBinding.getRoot());
    this.binding = rowNewsArticleBinding;
  }

  public void bind (final NewsArticle newsArticle){
    this.binding.title.setText(newsArticle.getTitle());
    this.binding.summary.setText(newsArticle.getSummary());
    this.binding.author.setText(newsArticle.getAuthor());
    this.binding.source.setText(newsArticle.getSource());
    // Date
    final Date date = DateTimeUtils.toDate(newsArticle.getDate().toInstant());
    this.binding.publishedAt.setText(PRETTY_TIME.format(date));
    // Image
    if(newsArticle.getImgUrl() != null){
      this.binding.image.setImageURI(newsArticle.getImgUrl());
    } else {
      this.binding.image.setImageResource(R.drawable.ic_launcher_background);
    }

  }
}
