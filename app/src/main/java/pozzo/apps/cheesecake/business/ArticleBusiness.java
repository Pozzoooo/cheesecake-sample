package pozzo.apps.cheesecake.business;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

import pozzo.apps.cheesecake.model.Article;
import pozzo.apps.cheesecake.network.ApiSingleton;
import pozzo.apps.cheesecake.util.Log;

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

	/**
	 * Sync local stoage with cloud.
	 *
	 * @return Success.
	 */
	public boolean syncArticles() {
		List<Article> articles;
		try {
			articles = ApiSingleton.getApi().getArticles();
		} catch (Exception e) {
			return false;//TODO Handle network error with properly message
		}

		try {
			ActiveAndroid.beginTransaction();
			//TODO Bad way to sync.. you just lost all you "readAt" data =(
			new Delete().from(Article.class).execute();
			for (Article it : articles)
				it.save();
			ActiveAndroid.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			Log.e(e);//Not sure what can go wrong, so send it to me logger.
		} finally {
			ActiveAndroid.endTransaction();
		}
		return false;
	}

	/**
	 * Mark given article as read now.
	 *
	 * @param article read.
	 */
	public void readArticle(Article article) {
		article.setReadAt(System.currentTimeMillis());
		article.save();
	}
}
