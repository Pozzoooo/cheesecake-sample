package pozzo.apps.cheesecake.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import pozzo.apps.cheesecake.R;
import pozzo.apps.cheesecake.business.ArticleBusiness;
import pozzo.apps.cheesecake.model.Article;
import pozzo.apps.cheesecake.ui.adapter.ArticleAdapter;

/**
 * List all our articles.
 *
 * @author Luiz Gustavo Pozzo
 * @since 2016-03-12
 */
public class ArticleListActivity extends AppCompatActivity {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_list);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(getTitle());

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		View recyclerView = findViewById(R.id.article_list);
		assert recyclerView != null;
		setupRecyclerView((RecyclerView) recyclerView);

		if (findViewById(R.id.article_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}
	}

	/**
	 * Initi recyclerview.
	 */
	private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
		new AsyncTask<Void, Void, List<Article>>() {
			@Override
			protected List<Article> doInBackground(Void... params) {
				return new ArticleBusiness().getAll();
			}

			@Override
			protected void onPostExecute(List<Article> articles) {
				if(articles == null || articles.isEmpty())
					refresh();
				else
					recyclerView.setAdapter(new ArticleAdapter(articles));
			}
		}.execute();
	}

	/**
	 * Refresh article list with server.
	 */
	private void refresh() {

	}
}
