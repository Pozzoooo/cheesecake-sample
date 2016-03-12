package pozzo.apps.cheesecake.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import pozzo.apps.cheesecake.R;
import pozzo.apps.cheesecake.model.Article;

/**
 * This is show an article detail.
 *
 * @author Luiz Gustavo Pozzo
 * @since 2016-03-12
 */
public class ArticleDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String PARAM_ARTICLE = "article";
	private Article article;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ArticleDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle arguments = getArguments();
		if (arguments.containsKey(PARAM_ARTICLE)) {
			article = arguments.getParcelable(PARAM_ARTICLE);

			Activity activity = getActivity();
			CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbarLayout);
			if (appBarLayout != null) {
				//TODO Title will not show completely... where should I put it?
				appBarLayout.setTitle(article.getTitle());
				ImageView iParallax = (ImageView) appBarLayout.findViewById(R.id.iParallax);
				ImageLoader.getInstance().displayImage(article.getImage(), iParallax);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.article_detail, container, false);

		((TextView) rootView.findViewById(R.id.article_detail)).setText(article.getContent());

		return rootView;
	}
}
