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

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import cl.ucn.disc.dsm.pag.news.R;
import cl.ucn.disc.dsm.pag.news.databinding.ActivityMainBinding;
import cl.ucn.disc.dsm.pag.news.model.NewsArticle;
import cl.ucn.disc.dsm.pag.news.services.NewsService;
import cl.ucn.disc.dsm.pag.news.services.newsapi.NewsApiNewsService;
import java.util.List;
import org.apache.commons.lang3.time.StopWatch;

public class MainActivity extends AppCompatActivity {

  private NewsArticleViewHolderAdapter adapter;
  private NewsService service;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Binding to the main layout
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    this.setSupportActionBar(binding.toolbar);

    // Adapter
    this.adapter = new NewsArticleViewHolderAdapter();
    binding.rvNews.setAdapter(this.adapter);
    binding.rvNews.setLayoutManager(new LinearLayoutManager(this));
    binding.rvNews.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    // Service
    this.service = new NewsApiNewsService();

    // //TODO: Refresh button
    binding.swlRefresh.setOnRefreshListener(() -> {
      AsyncTask.execute(()-> {
        final StopWatch stopWatch = StopWatch.createStarted();

        try {

          // Get news from the service (News API)
          final List<NewsArticle> news = this.service.getNews(50);

          // (in UI)
          this.runOnUiThread(() -> {
            // Set adapter
            this.adapter.setNews(news);

            // 3. Show a Toast!
            Toast.makeText(this, "Done: " + stopWatch, Toast.LENGTH_SHORT).show();
          });
          }
          catch(Exception ex){

            this.runOnUiThread(() -> {
              // Build error message
              final StringBuffer sb = new StringBuffer("Error: ");
              sb.append(ex.getMessage());
              if (ex.getCause() != null) {
                sb.append(", ");
                sb.append(ex.getCause().getMessage());
              }
              // Show a Toast!
              Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
            });
          } finally{
          //binding.swlRefresh.setRefreshing(false);
        }

        });
    });

  }
}