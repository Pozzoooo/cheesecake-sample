package pozzo.apps.cheesecake.network;

import com.google.gson.JsonElement;
import com.squareup.okhttp.OkHttpClient;

import pozzo.apps.cheesecake.BuildConfig;
import pozzo.apps.cheesecake.util.GsonPoz;
import pozzo.apps.cheesecake.util.Log;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

/**
 * Our core to make network request.
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/16
 */
public class ApiSingleton {
	public static final String BODY_TYPE = "application/json";
	private static Api api;

	public static Api getApi() {
		if(api == null) {
			RestAdapter restAdapter = getRestAdapter(BuildConfig.ENDPOINT);
			api = restAdapter.create(Api.class);
		}
		return api;
	}

	/**
	 * Rest adapter to crate api instances.
	 */
	private static RestAdapter getRestAdapter(String endpoint) {
		return new RestAdapter.Builder()
				.setEndpoint(endpoint)
				.setRequestInterceptor(new SendIntercept())
				.setErrorHandler(new HandleError())
				.setConverter(new GsonConverter(GsonPoz.getGson()))
				.setClient(new OkClient(new OkHttpClient()))
//					.setLogLevel(RestAdapter.LogLevel.FULL)
				.build();
	}

	/**
	 * Just to make it easy to parse to json.
	 *
	 * @param response received from server.
	 * @return json value.
	 */
	public static JsonElement responseToJson(Response response) {
		if(response == null || response.getBody() == null)
			return null;
		String body = new String(((TypedByteArray) response.getBody()).getBytes());
		return GsonPoz.getParser().parse(body);
	}

	/**
	 * Default headers.
	 */
	private static class SendIntercept implements RequestInterceptor {
		@Override
		public void intercept(RequestFacade request) {
			request.addHeader("Content-Type", BODY_TYPE);
			request.addHeader("Accept", BODY_TYPE);
		}
	}

	/**
	 * Better watch for problems.
	 */
	private static class HandleError implements ErrorHandler {
		@Override
		public Throwable handleError(RetrofitError cause) {
			Log.e(cause);
			return cause;
		}
	}
}
