package pozzo.apps.cheesecake.model;

import android.os.Parcel;
import android.os.Parcelable;
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
public class Article extends BaseModel implements Parcelable {
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

	@Column(name = "readAt")
	private long readAt;

	public Article() {
	}

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

	public long getReadAt() {
		return readAt;
	}

	public void setReadAt(long readAt) {
		this.readAt = readAt;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(website);
		dest.writeString(authors);
		dest.writeString(date);
		dest.writeString(content);
		dest.writeString(image);
		dest.writeLong(readAt);
		dest.writeLong(getId());
	}

	protected Article(Parcel in) {
		title = in.readString();
		website = in.readString();
		authors = in.readString();
		date = in.readString();
		content = in.readString();
		image = in.readString();
		readAt = in.readLong();
		setId(in.readLong());
	}

	public static final Creator<Article> CREATOR = new Creator<Article>() {
		@Override
		public Article createFromParcel(Parcel in) {
			return new Article(in);
		}

		@Override
		public Article[] newArray(int size) {
			return new Article[size];
		}
	};
}
