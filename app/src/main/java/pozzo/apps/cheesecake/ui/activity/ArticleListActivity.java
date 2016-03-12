package pozzo.apps.cheesecake.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import pozzo.apps.cheesecake.R;
import pozzo.apps.cheesecake.business.ArticleBusiness;
import pozzo.apps.cheesecake.model.Article;
import pozzo.apps.cheesecake.ui.adapter.ArticleAdapter;
import pozzo.apps.cheesecake.ui.fragment.ArticleDetailFragment;

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
	private boolean isTwoPanels;

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
		srRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refresh();
			}
		});

		ViewGroup articleDetails = (ViewGroup) findViewById(R.id.vgArticleDetails);
		if (articleDetails != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			isTwoPanels = true;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		//So we make sure all data is localy fresh
		setupRecyclerView();
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
					recyclerView.setAdapter(new ArticleAdapter(
							articles, isTwoPanels ? showDetailsOnSide : openDetailsActivity));
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

	/**
	 * Shows article details on the side panel.
 	 */
	private View.OnClickListener showDetailsOnSide = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Article article = (Article) v.getTag();

			Bundle arguments = new Bundle();
			arguments.putParcelable(ArticleDetailFragment.PARAM_ARTICLE, article);
			ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.vgArticleDetails, fragment)
					.commit();
		}
	};

	/**
	 * User wants to know more about an item.
	 */
	private View.OnClickListener openDetailsActivity = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Article article = (Article) v.getTag();
			Intent intent = new Intent(ArticleListActivity.this, ArticleDetailActivity.class);
			intent.putExtra(ArticleDetailFragment.PARAM_ARTICLE, article);
			startActivity(intent);
		}
	};

}
