package kaaes.spotify.webapi.android;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Creates and configures a REST adapter for Spotify Web API.
 * <p/>
 * Basic usage:
 * SpotifyApi wrapper = new SpotifyApi();
 * <p/>
 * Setting access token is optional for certain endpoints
 * so if you know you'll only use the ones that don't require authorisation
 * you can skip this step:
 * wrapper.setAccessToken(authenticationResponse.getAccessToken());
 * <p/>
 * SpotifyService spotify = wrapper.getService();
 * <p/>
 * Album album = spotify.getAlbum("2dIGnmEIy1WZIcZCFSj6i8");
 */
public class SpotifyApi {
    public static final String TAG = SpotifyApi.class.getSimpleName();

    /**
     * Main Spotify Web API endpoint
     */
    public static final String SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com/";

    /**
     * The request interceptor that will add the header with OAuth
     * token to every request made with the wrapper.
     */
    private class WebApiAuthenticator implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.d(TAG, "intercept: " + request.url());
            if (mAccessToken != null) {
                request = request.newBuilder().addHeader("Authorization", "Bearer " + mAccessToken).build();
            }
            return chain.proceed(request);
        }
    }

    private final SpotifyService mSpotifyService;

    private String mAccessToken;

    /**
     * New instance of SpotifyApi.
     *
     * @param retrofit The {@link Retrofit} instance to use when creating the {@link SpotifyService}
     */
    public SpotifyApi(Retrofit retrofit) {
        mSpotifyService = createService(retrofit);
    }

    private SpotifyService createService(Retrofit retrofit) {
        return retrofit.create(SpotifyService.class);
    }

    /**
     * Creates a basic {@link retrofit2.Retrofit.Builder} which uses
     * {@link #SPOTIFY_WEB_API_ENDPOINT} as the base URL and a {@link GsonConverterFactory}
     *
     * @return The builder
     */
    public Retrofit.Builder createBaseRetrofitBuilder() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(SPOTIFY_WEB_API_ENDPOINT);
    }

    /**
     * Creates a basic {@link okhttp3.OkHttpClient.Builder} which automatically applies the access
     * token to all calls
     *
     * @return The builder
     */
    public OkHttpClient.Builder createBaseOkHttpClientBuilder() {
        return new OkHttpClient.Builder().addNetworkInterceptor(new WebApiAuthenticator());
    }

    /**
     * New instance of SpotifyApi.
     */
    public SpotifyApi() {
        final Retrofit retrofit = createBaseRetrofitBuilder()
                .callFactory(createBaseOkHttpClientBuilder().build())
                .build();
        mSpotifyService = createService(retrofit);
    }

    /**
     * Sets access token on the wrapper.
     * Use to set or update token with the new value.
     * If you want to remove token set it to null.
     *
     * @param accessToken The token to set on the wrapper.
     * @return The instance of the wrapper.
     */
    public SpotifyApi setAccessToken(String accessToken) {
        mAccessToken = accessToken;
        return this;
    }

    /**
     * @return The SpotifyService instance
     */
    public SpotifyService getService() {
        return mSpotifyService;
    }
}
