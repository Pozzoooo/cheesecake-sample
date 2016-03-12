package pozzo.apps.cheesecake.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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

	private ArticleBusiness articleBusiness;
	private RecyclerView recyclerView;
	private SwipeRefreshLayout srRefresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_list);

		articleBusiness = new ArticleBusiness();

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (toolbar != null)
			toolbar.setTitle(getTitle());

		recyclerView = (RecyclerView) findViewById(R.id.article_list);
		srRefresh = (SwipeRefreshLayout) findViewById(R.id.srRefresh);
		setupRecyclerView();
		srRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refresh();
			}
		});

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
	private void setupRecyclerView() {
		new AsyncTask<Void, Void, List<Article>>() {
			@Override
			protected List<Article> doInBackground(Void... params) {
				return articleBusiness.getAll();
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
		new AsyncTask<Void, Void, Boolean>() {
			@Override
			protected void onPreExecute() {
				setIsRefreshing(true);
			}

			@Override
			protected Boolean doInBackground(Void... params) {
				return articleBusiness.syncArticles();
			}

			@Override
			protected void onPostExecute(Boolean aBoolean) {
				if(isFinishing())
					return;//User left before refresh =[

				setIsRefreshing(false);
				if(aBoolean) {
					setupRecyclerView();
				} else {
					Toast.makeText(ArticleListActivity.this,
							R.string.error_syncFailed, Toast.LENGTH_SHORT).show();
				}
			}
		}.execute();
	}

	/**
	 * Feedback user,
	 *
	 * @param state Current state.
	 */
	private void setIsRefreshing(boolean state) {
		srRefresh.setRefreshing(state);
	}
}
