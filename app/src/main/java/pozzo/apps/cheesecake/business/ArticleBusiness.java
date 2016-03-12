package pozzo.apps.cheesecake.business;

import com.activeandroid.query.Select;

import java.util.List;

import pozzo.apps.cheesecake.model.Article;

/**
 * Where we handle our business logic related to {@link pozzo.apps.cheesecake.model.Article}.
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/16
 */
public class ArticleBusiness {

	/**
	 * @return All articles saved on our local storage.
	 */
	public List<Article> getAll() {
		return new Select().from(Article.class).execute();
	}
}
