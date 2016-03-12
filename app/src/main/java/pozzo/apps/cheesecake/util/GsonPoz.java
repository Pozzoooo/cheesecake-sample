package pozzo.apps.cheesecake.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.lang.reflect.Modifier;

/**
 * Lets maintain it default in all the project.
 *
 * @author Luiz Gustavo Pozzo
 * @since 07/03/16
 */
public class GsonPoz {
	public static GsonBuilder getGsonBuilder() {
		return new GsonBuilder()
				.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	}

	public static Gson getGson() {
		return getGsonBuilder().create();
	}

	public static JsonParser getParser() {
		return new JsonParser();
	}
}
