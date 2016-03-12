package pozzo.apps.cheesecake.network;

import java.util.List;

import pozzo.apps.cheesecake.model.Article;
import retrofit.http.GET;

/**
 * Interface to access our web services.
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/2016
 */
public interface Api {
	/**
	 * Get all articles.
	 */
	@GET("/")
	List<Article> getArticles();
}
