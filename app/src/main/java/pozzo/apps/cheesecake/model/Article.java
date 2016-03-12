package pozzo.apps.cheesecake.model;

import android.provider.BaseColumns;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Represents an Article to be read.
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/16
 */
@Table(name = "Article", id = BaseColumns._ID)
public class Article extends BaseModel {
	@SerializedName("title")
	@Column(name = "title")
	private String title;

	@SerializedName("website")
	@Column(name = "website")
	private String website;

	@SerializedName("authors")
	@Column(name = "authors")
	private String authors;

	@SerializedName("date")
	@Column(name = "date")
	private String date;

	@SerializedName("content")
	@Column(name = "content")
	private String content;

	@SerializedName("image")
	@Column(name = "image")
	private String image;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
