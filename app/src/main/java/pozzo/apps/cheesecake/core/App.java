package pozzo.apps.cheesecake.core;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.splunk.mint.Mint;

/**
 * Here is where everything begins (the start of the world, or at least the app...).
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/16
 */
public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		//Init our bug tracker
		Mint.initAndStartSession(this, "950a2ed0");

		//Database
		ActiveAndroid.initialize(this);

		//Load and cache images
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.resetViewBeforeLoading(true).build();
		ImageLoader.getInstance().init(
				new ImageLoaderConfiguration.Builder(this)
				.defaultDisplayImageOptions(options).build());
	}
}
