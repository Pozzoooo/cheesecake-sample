package pozzo.apps.cheesecake.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import pozzo.apps.cheesecake.R;
import pozzo.apps.cheesecake.model.Article;

/**
 * Adapter to show Articles in a list.
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/16
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
	private List<Article> articles;

	public ArticleAdapter(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.adapter_article, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Article article = articles.get(position);

		holder.eTitle.setText(article.getTitle());
		holder.eAuthors.setText(article.getAuthors());
		holder.eDate.setText(article.getDate());
		ImageLoader.getInstance().displayImage(article.getImage(), holder.iImage);
	}

	@Override
	public int getItemCount() {
		return articles.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView eTitle;
		TextView eAuthors;
		TextView eDate;
		ImageView iImage;

		public ViewHolder(View itemView) {
			super(itemView);

			eTitle = (TextView) itemView.findViewById(R.id.eTitle);
			eAuthors = (TextView) itemView.findViewById(R.id.eAuthors);
			eDate = (TextView) itemView.findViewById(R.id.eDate);
			iImage = (ImageView) itemView.findViewById(R.id.iImage);
		}
	}
}
