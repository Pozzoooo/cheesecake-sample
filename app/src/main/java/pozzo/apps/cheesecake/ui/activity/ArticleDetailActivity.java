package pozzo.apps.cheesecake.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import pozzo.apps.cheesecake.R;
import pozzo.apps.cheesecake.business.ArticleBusiness;
import pozzo.apps.cheesecake.model.Article;
import pozzo.apps.cheesecake.ui.fragment.ArticleDetailFragment;

/**
 * Holds the fragment to show an article details.
 *
 * @author Luiz Gustavo Pozzo
 * @since 2016-03-12
 */
public class ArticleDetailActivity extends AppCompatActivity {
	private Article article;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_detail);
		Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
		setSupportActionBar(toolbar);

		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		if (savedInstanceState == null) {
			article = getIntent().getParcelableExtra(ArticleDetailFragment.PARAM_ARTICLE);

			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putParcelable(ArticleDetailFragment.PARAM_ARTICLE, article);
			ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.vgArticleDetails, fragment)
					.commit();
		}

		final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		if (fab != null) {
			fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new ArticleBusiness().readArticle(article);
					fab.setVisibility(View.GONE);
				}
			});
			if(article.getReadAt() != 0)
				fab.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			navigateUpTo(new Intent(this, ArticleListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
