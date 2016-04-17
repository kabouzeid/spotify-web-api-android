package kaaes.spotify.webapi.android;

import java.util.Map;

import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.Albums;
import kaaes.spotify.webapi.android.models.AlbumsPager;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsCursorPager;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.AudioFeaturesTrack;
import kaaes.spotify.webapi.android.models.AudioFeaturesTracks;
import kaaes.spotify.webapi.android.models.CategoriesPager;
import kaaes.spotify.webapi.android.models.Category;
import kaaes.spotify.webapi.android.models.FeaturedPlaylists;
import kaaes.spotify.webapi.android.models.NewReleases;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistFollowPrivacy;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.PlaylistsPager;
import kaaes.spotify.webapi.android.models.Recommendations;
import kaaes.spotify.webapi.android.models.Result;
import kaaes.spotify.webapi.android.models.SavedAlbum;
import kaaes.spotify.webapi.android.models.SavedTrack;
import kaaes.spotify.webapi.android.models.SeedsGenres;
import kaaes.spotify.webapi.android.models.SnapshotId;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import kaaes.spotify.webapi.android.models.TracksToRemove;
import kaaes.spotify.webapi.android.models.TracksToRemoveWithPosition;
import kaaes.spotify.webapi.android.models.UserPrivate;
import kaaes.spotify.webapi.android.models.UserPublic;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SpotifyService {

    /**
     * The maximum number of objects to return.
     */
    String LIMIT = "limit";

    /**
     * The index of the first playlist to return. Default: 0 (the first object).
     * Use with limit to get the next set of objects (albums, playlists, etc).
     */
    String OFFSET = "offset";

    /**
     * A comma-separated list of keywords that will be used to filter the response.
     * Valid values are: {@code album}, {@code single}, {@code appears_on}, {@code compilation}
     */
    String ALBUM_TYPE = "album_type";

    /**
     * The country: an ISO 3166-1 alpha-2 country code.
     * Limit the response to one particular geographical market.
     * Synonym to {@link #COUNTRY}
     */
    String MARKET = "market";

    /**
     * Same as {@link #MARKET}
     */
    String COUNTRY = "country";

    /**
     * The desired language, consisting of a lowercase ISO 639 language code
     * and an uppercase ISO 3166-1 alpha-2 country code, joined by an underscore.
     * For example: es_MX, meaning "Spanish (Mexico)".
     */
    String LOCALE = "locale";

    /**
     * Filters for the query: a comma-separated list of the fields to return.
     * If omitted, all fields are returned.
     */
    String FIELDS = "fields";

    /**
     * A timestamp in ISO 8601 format: yyyy-MM-ddTHH:mm:ss. Use this parameter to
     * specify the user's local time to get results tailored for that specific date
     * and time in the day. If not provided, the response defaults to the current UTC time
     */
    String TIMESTAMP = "timestamp";

    String TIME_RANGE = "time_range";


    /************
     * Profiles *
     ************/

    /**
     * Get the currently logged in user profile information.
     * The contents of the User object may differ depending on application's scope.
     *
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-current-users-profile/">Get Current User's Profile</a>
     */


    /**
     * Get the currently logged in user profile information.
     * The contents of the User object may differ depending on application's scope.
     *
     * @return The current user
     * @see <a href="https://developer.spotify.com/web-api/get-current-users-profile/">Get Current User's Profile</a>
     */
    @GET("/v1/me")
    Call<UserPrivate> getMe();

    /**
     * Get a user's profile information.
     *
     * @param userId   The user's User ID
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-users-profile/">Get User's Public Profile</a>
     */


    /**
     * Get a user's profile information.
     *
     * @param userId The user's User ID
     * @return The user's profile information.
     * @see <a href="https://developer.spotify.com/web-api/get-users-profile/">Get User's Public Profile</a>
     */
    @GET("/v1/users/{id}")
    Call<UserPublic> getUser(@Path("id") String userId);


    /*************
     * Playlists *
     *************/

    /**
     * Get a list of the playlists owned or followed by the current Spotify user.
     *
     * @param callback Callback method.
     */


    /**
     * Get a list of the playlists owned or followed by the current Spotify user.
     *
     * @return List of user's playlists wrapped in a {@code Pager} object
     */
    @GET("/v1/me/playlists")
    Call<Pager<PlaylistSimple>> getMyPlaylists();

    /**
     * Get a list of the playlists owned or followed by the current Spotify user.
     *
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-a-list-of-current-users-playlists/">endpoint documentation</a>
     * @param callback Callback method.
     */


    /**
     * Get a list of the playlists owned or followed by the current Spotify user.
     *
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-a-list-of-current-users-playlists/">endpoint documentation</a>
     * @return List of user's playlists wrapped in a {@code Pager} object
     */
    @GET("/v1/me/playlists")
    Call<Pager<PlaylistSimple>> getMyPlaylists(@QueryMap Map<String, Object> options);

    /**
     * Get a list of the playlists owned or followed by a Spotify user.
     *
     * @param userId   The user's Spotify user ID.
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-list-users-playlists/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-list-users-playlists/">Get a List of a User’s Playlists</a>
     */


    /**
     * Get a list of the playlists owned or followed by a Spotify user.
     *
     * @param userId  The user's Spotify user ID.
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-list-users-playlists/">endpoint documentation</a>
     * @return List of user's playlists wrapped in a {@code Pager} object
     * @see <a href="https://developer.spotify.com/web-api/get-list-users-playlists/">Get a List of a User’s Playlists</a>
     */
    @GET("/v1/users/{id}/playlists")
    Call<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId, @QueryMap Map<String, Object> options);

    /**
     * Get a list of the playlists owned or followed by a Spotify user.
     *
     * @param userId   The user's Spotify user ID.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-list-users-playlists/">Get a List of a User’s Playlists</a>
     */


    /**
     * Get a list of the playlists owned or followed by a Spotify user.
     *
     * @param userId The user's Spotify user ID.
     * @return List of user's playlists wrapped in a {@code Pager} object
     * @see <a href="https://developer.spotify.com/web-api/get-list-users-playlists/">Get a List of a User’s Playlists</a>
     */
    @GET("/v1/users/{id}/playlists")
    Call<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId);

    /**
     * Get a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @param options    Optional parameters. For list of supported parameters see
     *                   <a href="https://developer.spotify.com/web-api/get-playlist/">endpoint documentation</a>
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a>
     */


    /**
     * Get a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @param options    Optional parameters. For list of supported parameters see
     *                   <a href="https://developer.spotify.com/web-api/get-playlist/">endpoint documentation</a>
     * @return Requested Playlist.
     * @see <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a>
     */
    @GET("/v1/users/{user_id}/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    /**
     * Get a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a>
     */


    /**
     * Get a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @return Requested Playlist.
     * @see <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a>
     */
    @GET("/v1/users/{user_id}/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    /**
     * Get full details of the tracks of a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @param options    Optional parameters. For list of supported parameters see
     *                   <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">endpoint documentation</a>
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist’s Tracks</a>
     */


    /**
     * Get full details of the tracks of a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @param options    Optional parameters. For list of supported parameters see
     *                   <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">endpoint documentation</a>
     * @return List of playlist's tracks wrapped in a {@code Pager} object
     * @see <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist’s Tracks</a>
     */
    @GET("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    /**
     * Get full details of the tracks of a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist’s Tracks</a>
     */


    /**
     * Get full details of the tracks of a playlist owned by a Spotify user.
     *
     * @param userId     The user's Spotify user ID.
     * @param playlistId The Spotify ID for the playlist.
     * @return List of playlist's tracks wrapped in a {@code Pager} object
     * @see <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist’s Tracks</a>
     */
    @GET("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    /**
     * Create a playlist
     *
     * @param userId   The playlist's owner's User ID
     * @param body     The body parameters
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/create-playlist/">Create a Playlist</a>
     */


    /**
     * Create a playlist
     *
     * @param userId  The playlist's owner's User ID
     * @param options The body parameters
     * @return The created playlist
     * @see <a href="https://developer.spotify.com/web-api/create-playlist/">Create a Playlist</a>
     */
    @POST("/v1/users/{user_id}/playlists")
    Call<Playlist> createPlaylist(@Path("user_id") String userId, @Body Map<String, Object> options);

    /**
     * Add tracks to a playlist
     *
     * @param userId          The owner of the playlist
     * @param playlistId      The playlist's ID
     * @param queryParameters Query parameters
     * @param body            The body parameters
     * @return A snapshot ID (the version of the playlist)
     * @see <a href="https://developer.spotify.com/web-api/add-tracks-to-playlist/">Add Tracks to a Playlist</a>
     */
    @POST("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> queryParameters, @Body Map<String, Object> body);

    /**
     * Add tracks to a playlist
     *
     * @param userId          The owner of the playlist
     * @param playlistId      The playlist's Id
     * @param queryParameters Query parameters
     * @param body            The body parameters
     * @param callback        Callback method
     * @see <a href="https://developer.spotify.com/web-api/add-tracks-to-playlist/">Add Tracks to a Playlist</a>
     */


    /**
     * Remove one or more tracks from a user’s playlist.
     *
     * @param userId         The owner of the playlist
     * @param playlistId     The playlist's Id
     * @param tracksToRemove A list of tracks to remove
     * @param callback       Callback method
     * @see <a href="https://developer.spotify.com/web-api/remove-tracks-playlist/">Remove Tracks from a Playlist</a>
     */


    /**
     * Remove one or more tracks from a user’s playlist.
     *
     * @param userId         The owner of the playlist
     * @param playlistId     The playlist's Id
     * @param tracksToRemove A list of tracks to remove
     * @return A snapshot ID (the version of the playlist)
     * @see <a href="https://developer.spotify.com/web-api/remove-tracks-playlist/">Remove Tracks from a Playlist</a>
     */
    @DELETE("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove);

    /**
     * Remove one or more tracks from a user’s playlist.
     *
     * @param userId                     The owner of the playlist
     * @param playlistId                 The playlist's Id
     * @param tracksToRemoveWithPosition A list of tracks to remove, together with their specific positions
     * @param callback                   Callback method
     * @see <a href="https://developer.spotify.com/web-api/remove-tracks-playlist/">Remove Tracks from a Playlist</a>
     */


    /**
     * Remove one or more tracks from a user’s playlist.
     *
     * @param userId                     The owner of the playlist
     * @param playlistId                 The playlist's Id
     * @param tracksToRemoveWithPosition A list of tracks to remove, together with their specific positions
     * @return A snapshot ID (the version of the playlist)
     * @see <a href="https://developer.spotify.com/web-api/remove-tracks-playlist/">Remove Tracks from a Playlist</a>
     */
    @DELETE("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition);

    /**
     * Replace all the tracks in a playlist, overwriting its existing tracks. This powerful request can be useful for
     * replacing tracks, re-ordering existing tracks, or clearing the playlist.
     *
     * @param userId     The owner of the playlist
     * @param playlistId The playlist's Id
     * @param trackUris  A list of comma-separated track uris
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/replace-playlists-tracks/">Replace a Playlist’s Tracks</a>
     */


    /**
     * Replace all the tracks in a playlist, overwriting its existing tracks. This powerful request can be useful for
     * replacing tracks, re-ordering existing tracks, or clearing the playlist.
     *
     * @param userId     The owner of the playlist
     * @param playlistId The playlist's Id
     * @param trackUris  A list of comma-separated track uris
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/replace-playlists-tracks/">Replace a Playlist’s Tracks</a>
     */
    @PUT("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Result> replaceTracksInPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Body Object body);

    /**
     * Change a playlist’s name and public/private state. (The user must, of course, own the playlist.)
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The playlist's Id
     * @param body       The body parameters. For list of supported parameters see <a href="https://developer.spotify.com/web-api/change-playlist-details/">endpoint documentation</a>
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/change-playlist-details/">Change a Playlist's Details</a>
     */
    @PUT("/v1/users/{user_id}/playlists/{playlist_id}")
    Call<Result> changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    /**
     * Change a playlist’s name and public/private state. (The user must, of course, own the playlist.)
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The playlist's Id
     * @param body       The body parameters. For list of supported parameters see <a href="https://developer.spotify.com/web-api/change-playlist-details/">endpoint documentation</a>
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/change-playlist-details/">Change a Playlist's Details</a>
     */


    /**
     * Add the current user as a follower of a playlist.
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The Spotify ID of the playlist
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/follow-playlist/">Follow a Playlist</a>
     */


    /**
     * Add the current user as a follower of a playlist.
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The Spotify ID of the playlist
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/follow-playlist/">Follow a Playlist</a>
     */
    @PUT("/v1/users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    /**
     * Add the current user as a follower of a playlist.
     *
     * @param userId                The Spotify user ID of the user who owns the playlist.
     * @param playlistId            The Spotify ID of the playlist
     * @param playlistFollowPrivacy The privacy state of the playlist
     * @param callback              Callback method
     * @see <a href="https://developer.spotify.com/web-api/follow-playlist/">Follow a Playlist</a>
     */


    /**
     * Add the current user as a follower of a playlist.
     *
     * @param userId                The Spotify user ID of the user who owns the playlist.
     * @param playlistId            The Spotify ID of the playlist
     * @param playlistFollowPrivacy The privacy state of the playlist
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/follow-playlist/">Follow a Playlist</a>
     */
    @PUT("/v1/users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body PlaylistFollowPrivacy playlistFollowPrivacy);

    /**
     * Unfollow a Playlist
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The Spotify ID of the playlist
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/unfollow-playlist/">Unfollow a Playlist</a>
     */


    /**
     * Unfollow a Playlist
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The Spotify ID of the playlist
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/unfollow-playlist/">Unfollow a Playlist</a>
     */
    @DELETE("/v1/users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> unfollowPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    /**
     * Reorder a Playlist's tracks
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The Spotify ID of the playlist
     * @param body       The body parameters. For list of supported parameters see <a href="https://developer.spotify.com/web-api/reorder-playlists-tracks/">endpoint documentation</a>
     * @return A snapshot ID (the version of the playlist)
     * @see <a href="https://developer.spotify.com/web-api/reorder-playlists-tracks/">Reorder a Playlist</a>
     */
    @PUT("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> reorderPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    /**
     * Reorder a Playlist's tracks
     *
     * @param userId     The Spotify user ID of the user who owns the playlist.
     * @param playlistId The Spotify ID of the playlist
     * @param body       The body parameters. For list of supported parameters see <a href="https://developer.spotify.com/web-api/reorder-playlists-tracks/">endpoint documentation</a>
     * @param callback   Callback method
     * @see <a href="https://developer.spotify.com/web-api/reorder-playlists-tracks/">Reorder a Playlist</a>
     */


    /**********
     * Albums *
     **********/

    /**
     * Get Spotify catalog information for a single album.
     *
     * @param albumId  The Spotify ID for the album.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-album/">Get an Album</a>
     */


    /**
     * Get Spotify catalog information for a single album.
     *
     * @param albumId The Spotify ID for the album.
     * @return Requested album information
     * @see <a href="https://developer.spotify.com/web-api/get-album/">Get an Album</a>
     */
    @GET("/v1/albums/{id}")
    Call<Album> getAlbum(@Path("id") String albumId);

    /**
     * Get Spotify catalog information for a single album.
     *
     * @param albumId  The Spotify ID for the album.
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-album/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-album/">Get an Album</a>
     */


    /**
     * Get Spotify catalog information for a single album.
     *
     * @param albumId The Spotify ID for the album.
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-album/">endpoint documentation</a>
     * @return Requested album information
     * @see <a href="https://developer.spotify.com/web-api/get-album/">Get an Album</a>
     */
    @GET("/v1/albums/{id}")
    Call<Album> getAlbum(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    /**
     * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
     *
     * @param albumIds A comma-separated list of the Spotify IDs for the albums
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-several-albums/">Get Several Albums</a>
     */


    /**
     * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
     *
     * @param albumIds A comma-separated list of the Spotify IDs for the albums
     * @return Object whose key is "albums" and whose value is an array of album objects.
     * @see <a href="https://developer.spotify.com/web-api/get-several-albums/">Get Several Albums</a>
     */
    @GET("/v1/albums")
    Call<Albums> getAlbums(@Query("ids") String albumIds);

    /**
     * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
     *
     * @param albumIds A comma-separated list of the Spotify IDs for the albums
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-several-albums/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-several-albums/">Get Several Albums</a>
     */


    /**
     * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
     *
     * @param albumIds A comma-separated list of the Spotify IDs for the albums
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-several-albums/">endpoint documentation</a>
     * @return Object whose key is "albums" and whose value is an array of album objects.
     * @see <a href="https://developer.spotify.com/web-api/get-several-albums/">Get Several Albums</a>
     */
    @GET("/v1/albums")
    Call<Albums> getAlbums(@Query("ids") String albumIds, @QueryMap Map<String, Object> options);

    /**
     * Get Spotify catalog information about an album’s tracks.
     *
     * @param albumId The Spotify ID for the album.
     * @return List of simplified album objects wrapped in a Pager object
     * @see <a href="https://developer.spotify.com/web-api/get-albums-tracks/">Get an Album’s Tracks</a>
     */
    @GET("/v1/albums/{id}/tracks")
    Call<Pager<Track>> getAlbumTracks(@Path("id") String albumId);

    /**
     * Get Spotify catalog information about an album’s tracks.
     *
     * @param albumId  The Spotify ID for the album.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-albums-tracks/">Get an Album’s Tracks</a>
     */


    /**
     * Get Spotify catalog information about an album’s tracks.
     *
     * @param albumId  The Spotify ID for the album.
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-albums-tracks/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-albums-tracks/">Get an Album’s Tracks</a>
     */


    /**
     * Get Spotify catalog information about an album’s tracks.
     *
     * @param albumId The Spotify ID for the album.
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-albums-tracks/">endpoint documentation</a>
     * @return List of simplified album objects wrapped in a Pager object
     * @see <a href="https://developer.spotify.com/web-api/get-albums-tracks/">Get an Album’s Tracks</a>
     */
    @GET("/v1/albums/{id}/tracks")
    Call<Pager<Track>> getAlbumTracks(@Path("id") String albumId, @QueryMap Map<String, Object> options);


    /***********
     * Artists *
     ***********/

    /**
     * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
     *
     * @param artistId The Spotify ID for the artist.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-artist/">Get an Artist</a>
     */


    /**
     * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
     *
     * @param artistId The Spotify ID for the artist.
     * @return Requested artist information
     * @see <a href="https://developer.spotify.com/web-api/get-artist/">Get an Artist</a>
     */
    @GET("/v1/artists/{id}")
    Call<Artist> getArtist(@Path("id") String artistId);

    /**
     * Get Spotify catalog information for several artists based on their Spotify IDs.
     *
     * @param artistIds A comma-separated list of the Spotify IDs for the artists
     * @param callback  Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-several-artists/">Get Several Artists</a>
     */


    /**
     * Get Spotify catalog information for several artists based on their Spotify IDs.
     *
     * @param artistIds A comma-separated list of the Spotify IDs for the artists
     * @return An object whose key is "artists" and whose value is an array of artist objects.
     * @see <a href="https://developer.spotify.com/web-api/get-several-artists/">Get Several Artists</a>
     */
    @GET("/v1/artists")
    Call<Artists> getArtists(@Query("ids") String artistIds);

    /**
     * Get Spotify catalog information about an artist’s albums.
     *
     * @param artistId The Spotify ID for the artist.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-artists-albums/">Get an Artist's Albums</a>
     */


    /**
     * Get Spotify catalog information about an artist’s albums.
     *
     * @param artistId The Spotify ID for the artist.
     * @return An array of simplified album objects wrapped in a paging object.
     * @see <a href="https://developer.spotify.com/web-api/get-artists-albums/">Get an Artist's Albums</a>
     */
    @GET("/v1/artists/{id}/albums")
    Call<Pager<Album>> getArtistAlbums(@Path("id") String artistId);

    /**
     * Get Spotify catalog information about an artist’s albums.
     *
     * @param artistId The Spotify ID for the artist.
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-artists-albums/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-artists-albums/">Get an Artist's Albums</a>
     */


    /**
     * Get Spotify catalog information about an artist’s albums.
     *
     * @param artistId The Spotify ID for the artist.
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-artists-albums/">endpoint documentation</a>
     * @return An array of simplified album objects wrapped in a paging object.
     * @see <a href="https://developer.spotify.com/web-api/get-artists-albums/">Get an Artist's Albums</a>
     */
    @GET("/v1/artists/{id}/albums")
    Call<Pager<Album>> getArtistAlbums(@Path("id") String artistId, @QueryMap Map<String, Object> options);

    /**
     * Get Spotify catalog information about an artist’s top tracks by country.
     *
     * @param artistId The Spotify ID for the artist.
     * @param country  The country: an ISO 3166-1 alpha-2 country code.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-artists-top-tracks/">Get an Artist’s Top Tracks</a>
     */


    /**
     * Get Spotify catalog information about an artist’s top tracks by country.
     *
     * @param artistId The Spotify ID for the artist.
     * @param country  The country: an ISO 3166-1 alpha-2 country code.
     * @return An object whose key is "tracks" and whose value is an array of track objects.
     * @see <a href="https://developer.spotify.com/web-api/get-artists-top-tracks/">Get an Artist’s Top Tracks</a>
     */
    @GET("/v1/artists/{id}/top-tracks")
    Call<Tracks> getArtistTopTrack(@Path("id") String artistId, @Query("country") String country);

    /**
     * Get Spotify catalog information about artists similar to a given artist.
     *
     * @param artistId The Spotify ID for the artist.
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-related-artists/">Get an Artist’s Related Artists</a>
     */


    /**
     * Get Spotify catalog information about artists similar to a given artist.
     *
     * @param artistId The Spotify ID for the artist.
     * @return An object whose key is "artists" and whose value is an array of artist objects.
     * @see <a href="https://developer.spotify.com/web-api/get-related-artists/">Get an Artist’s Related Artists</a>
     */
    @GET("/v1/artists/{id}/related-artists")
    Call<Artists> getRelatedArtists(@Path("id") String artistId);


    /**********
     * Tracks *
     **********/

    /**
     * Get Spotify catalog information for a single track identified by their unique Spotify ID.
     *
     * @param trackId  The Spotify ID for the track.
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-track/">Get a Track</a>
     */


    /**
     * Get Spotify catalog information for a single track identified by their unique Spotify ID.
     *
     * @param trackId The Spotify ID for the track.
     * @return Requested track information
     * @see <a href="https://developer.spotify.com/web-api/get-track/">Get a Track</a>
     */
    @GET("/v1/tracks/{id}")
    Call<Track> getTrack(@Path("id") String trackId);

    /**
     * Get Spotify catalog information for a single track identified by their unique Spotify ID.
     *
     * @param trackId  The Spotify ID for the track.
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-track/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-track/">Get a Track</a>
     */


    /**
     * Get Spotify catalog information for a single track identified by their unique Spotify ID.
     *
     * @param trackId The Spotify ID for the track.
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-track/">endpoint documentation</a>
     * @return Requested track information
     * @see <a href="https://developer.spotify.com/web-api/get-track/">Get a Track</a>
     */
    @GET("/v1/tracks/{id}")
    Call<Track> getTrack(@Path("id") String trackId, @QueryMap Map<String, Object> options);

    /**
     * Get Several Tracks
     *
     * @param trackIds A comma-separated list of the Spotify IDs for the tracks
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-several-tracks/">Get Several Tracks</a>
     */


    /**
     * Get Several Tracks
     *
     * @param trackIds A comma-separated list of the Spotify IDs for the tracks
     * @return An object whose key is "tracks" and whose value is an array of track objects.
     * @see <a href="https://developer.spotify.com/web-api/get-several-tracks/">Get Several Tracks</a>
     */
    @GET("/v1/tracks")
    Call<Tracks> getTracks(@Query("ids") String trackIds);

    /**
     * Get Several Tracks
     *
     * @param trackIds A comma-separated list of the Spotify IDs for the tracks
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-several-tracks/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-several-tracks/">Get Several Tracks</a>
     */


    /**
     * Get Several Tracks
     *
     * @param trackIds A comma-separated list of the Spotify IDs for the tracks
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-several-tracks/">endpoint documentation</a>
     * @return An object whose key is "tracks" and whose value is an array of track objects.
     * @see <a href="https://developer.spotify.com/web-api/get-several-tracks/">Get Several Tracks</a>
     */
    @GET("/v1/tracks")
    Call<Tracks> getTracks(@Query("ids") String trackIds, @QueryMap Map<String, Object> options);


    /**********
     * Browse *
     **********/

    /**
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-list-featured-playlists/">Get a List of Featured Playlists</a>
     */


    /**
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @return A FeaturedPlaylists object with the featured playlists
     * @see <a href="https://developer.spotify.com/web-api/get-list-featured-playlists/">Get a List of Featured Playlists</a>
     */
    @GET("/v1/browse/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists();

    /**
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-list-featured-playlists/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-list-featured-playlists/">Get a List of Featured Playlists</a>
     */


    /**
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-list-featured-playlists/">endpoint documentation</a>
     * @return n FeaturedPlaylists object with the featured playlists
     * @see <a href="https://developer.spotify.com/web-api/get-list-featured-playlists/">Get a List of Featured Playlists</a>
     */
    @GET("/v1/browse/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists(@QueryMap Map<String, Object> options);

    /**
     * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-list-new-releases/">Get a List of New Releases</a>
     */


    /**
     * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @return A NewReleases object with the new album releases
     * @see <a href="https://developer.spotify.com/web-api/get-list-new-releases/">Get a List of New Releases</a>
     */
    @GET("/v1/browse/new-releases")
    Call<NewReleases> getNewReleases();

    /**
     * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-list-new-releases/">endpoint documentation</a>
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/get-list-new-releases/">Get a List of New Releases</a>
     */


    /**
     * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
     *
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-list-new-releases/">endpoint documentation</a>
     * @return A NewReleases object with the new album releases
     * @see <a href="https://developer.spotify.com/web-api/get-list-new-releases/">Get a List of New Releases</a>
     */
    @GET("/v1/browse/new-releases")
    Call<NewReleases> getNewReleases(@QueryMap Map<String, Object> options);

    /**
     * Retrieve Spotify categories. Categories used to tag items in
     * Spotify (on, for example, the Spotify player’s “Browse” tab).
     *
     * @param options  Optional parameters.
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-list-categories/">Get a List of Categories</a>
     */


    /**
     * Retrieve Spotify categories. Categories used to tag items in
     * Spotify (on, for example, the Spotify player’s “Browse” tab).
     *
     * @param options Optional parameters.
     * @return A paging object containing categories.
     * @see <a href="https://developer.spotify.com/web-api/get-list-categories/">Get a List of Categories</a>
     */
    @GET("/v1/browse/categories")
    Call<CategoriesPager> getCategories(@QueryMap Map<String, Object> options);

    /**
     * Retrieve a Spotify category.
     *
     * @param categoryId The category's ID.
     * @param options    Optional parameters.
     * @param callback   Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-category/">Get a Spotify Category</a>
     */


    /**
     * Retrieve a Spotify category.
     *
     * @param categoryId The category's ID.
     * @param options    Optional parameters.
     * @return A Spotify category.
     * @see <a href="https://developer.spotify.com/web-api/get-category/">Get a Spotify Category</a>
     */
    @GET("/v1/browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    /**
     * Retrieve playlists for a Spotify Category.
     *
     * @param categoryId The category's ID.
     * @param options    Optional parameters.
     * @param callback   Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-categorys-playlists/">Get playlists for a Spotify Category</a>
     */


    /**
     * Retrieve playlists for a Spotify Category.
     *
     * @param categoryId The category's ID.
     * @param options    Optional parameters.
     * @return Playlists for a Spotify Category.
     * @see <a href="https://developer.spotify.com/web-api/get-categorys-playlists/">Get playlists for a Spotify Category</a>
     */
    @GET("/v1/browse/categories/{category_id}/playlists")
    Call<PlaylistsPager> getPlaylistsForCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);


    /************************
     * Library / Your Music *
     ************************/

    /**
     * Get a list of the songs saved in the current Spotify user’s “Your Music” library.
     *
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-tracks/">Get a User’s Saved Tracks</a>
     */


    /**
     * Get a list of the songs saved in the current Spotify user’s “Your Music” library.
     *
     * @return A paginated list of saved tracks
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-tracks/">Get a User’s Saved Tracks</a>
     */
    @GET("/v1/me/tracks")
    Call<Pager<SavedTrack>> getMySavedTracks();

    /**
     * Get a list of the songs saved in the current Spotify user’s “Your Music” library.
     *
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-users-saved-tracks/">endpoint documentation</a>
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-tracks/">Get a User’s Saved Tracks</a>
     */


    /**
     * Get a list of the songs saved in the current Spotify user’s “Your Music” library.
     *
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-users-saved-tracks/">endpoint documentation</a>
     * @return A paginated list of saved tracks
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-tracks/">Get a User’s Saved Tracks</a>
     */
    @GET("/v1/me/tracks")
    Call<Pager<SavedTrack>> getMySavedTracks(@QueryMap Map<String, Object> options);

    /**
     * Check if one or more tracks is already saved in the current Spotify user’s “Your Music” library.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the tracks
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/check-users-saved-tracks/">Check User’s Saved Tracks</a>
     */


    /**
     * Check if one or more tracks is already saved in the current Spotify user’s “Your Music” library.
     *
     * @param ids A comma-separated list of the Spotify IDs for the tracks
     * @return An array with boolean values that indicate whether the tracks are in the current Spotify user’s “Your Music” library.
     * @see <a href="https://developer.spotify.com/web-api/check-users-saved-tracks/">Check User’s Saved Tracks</a>
     */
    @GET("/v1/me/tracks/contains")
    Call<Boolean[]> containsMySavedTracks(@Query("ids") String ids);

    /**
     * Save one or more tracks to the current user’s “Your Music” library.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the tracks
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/save-tracks-user/">Save Tracks for User</a>
     */


    /**
     * Save one or more tracks to the current user’s “Your Music” library.
     *
     * @param ids A comma-separated list of the Spotify IDs for the tracks
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/save-tracks-user/">Save Tracks for User</a>
     */
    @PUT("/v1/me/tracks")
    Call<Result> addToMySavedTracks(@Query("ids") String ids);

    /**
     * Remove one or more tracks from the current user’s “Your Music” library.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the tracks
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/remove-tracks-user/">Remove User’s Saved Tracks</a>
     */


    /**
     * Remove one or more tracks from the current user’s “Your Music” library.
     *
     * @param ids A comma-separated list of the Spotify IDs for the tracks
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/remove-tracks-user/">Remove User’s Saved Tracks</a>
     */
    @DELETE("/v1/me/tracks")
    Call<Result> removeFromMySavedTracks(@Query("ids") String ids);

    /**
     * Get a list of the albums saved in the current Spotify user’s “Your Music” library.
     *
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-albums/">Get a User’s Saved Albums</a>
     */


    /**
     * Get a list of the albums saved in the current Spotify user’s “Your Music” library.
     *
     * @return A paginated list of saved albums
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-albums/">Get a User’s Saved Albums</a>
     */
    @GET("/v1/me/albums")
    Call<Pager<SavedAlbum>> getMySavedAlbums();

    /**
     * Get a list of the albums saved in the current Spotify user’s “Your Music” library.
     *
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-users-saved-albums/">endpoint documentation</a>
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-albums/">Get a User’s Saved Albums</a>
     */


    /**
     * Get a list of the albums saved in the current Spotify user’s “Your Music” library.
     *
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-users-saved-albums/">endpoint documentation</a>
     * @return A paginated list of saved albums
     * @see <a href="https://developer.spotify.com/web-api/get-users-saved-albums/">Get a User’s Saved Albums</a>
     */
    @GET("/v1/me/albums")
    Call<Pager<SavedAlbum>> getMySavedAlbums(@QueryMap Map<String, Object> options);

    /**
     * Check if one or more albums is already saved in the current Spotify user’s “Your Music” library.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the albums
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/check-users-saved-albums/">Check User’s Saved Albums</a>
     */


    /**
     * Check if one or more albums is already saved in the current Spotify user’s “Your Music” library.
     *
     * @param ids A comma-separated list of the Spotify IDs for the albums
     * @return An array with boolean values that indicate whether the albums are in the current Spotify user’s “Your Music” library.
     * @see <a href="https://developer.spotify.com/web-api/check-users-saved-albums/">Check User’s Saved Albums</a>
     */
    @GET("/v1/me/albums/contains")
    Call<Boolean[]> containsMySavedAlbums(@Query("ids") String ids);

    /**
     * Save one or more albums to the current user’s “Your Music” library.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the albums
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/save-albums-user/">Save Albums for User</a>
     */


    /**
     * Save one or more albums to the current user’s “Your Music” library.
     *
     * @param ids A comma-separated list of the Spotify IDs for the albums
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/save-albums-user/">Save Albums for User</a>
     */
    @PUT("/v1/me/albums")
    Call<Result> addToMySavedAlbums(@Query("ids") String ids);

    /**
     * Remove one or more albums from the current user’s “Your Music” library.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the albums
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/remove-albums-user/">Remove User’s Saved Albums</a>
     */


    /**
     * Remove one or more albums from the current user’s “Your Music” library.
     *
     * @param ids A comma-separated list of the Spotify IDs for the albums
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/remove-albums-user/">Remove User’s Saved Albums</a>
     */
    @DELETE("/v1/me/albums")
    Call<Result> removeFromMySavedAlbums(@Query("ids") String ids);

    /**********
     * Follow *
     **********/

    /**
     * Add the current user as a follower of one or more Spotify users.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the users
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/follow-artists-users/">Follow Artists or Users</a>
     */


    /**
     * Add the current user as a follower of one or more Spotify users.
     *
     * @param ids A comma-separated list of the Spotify IDs for the users
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/follow-artists-users/">Follow Artists or Users</a>
     */
    @PUT("/v1/me/following?type=user")
    Call<Result> followUsers(@Query("ids") String ids);

    /**
     * Add the current user as a follower of one or more artists.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the artists
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/follow-artists-users/">Follow Artists or Users</a>
     */


    /**
     * Add the current user as a follower of one or more Spotify artists.
     *
     * @param ids A comma-separated list of the Spotify IDs for the artists
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/follow-artists-users/">Follow Artists or Users</a>
     */
    @PUT("/v1/me/following?type=artist")
    Call<Result> followArtists(@Query("ids") String ids);

    /**
     * Remove the current user as a follower of one or more Spotify users.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the users
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/unfollow-artists-users/">Unfollow Artists or Users</a>
     */


    /**
     * Remove the current user as a follower of one or more Spotify users.
     *
     * @param ids A comma-separated list of the Spotify IDs for the users
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/unfollow-artists-users/">Unfollow Artists or Users</a>
     */
    @DELETE("/v1/me/following?type=user")
    Call<Result> unfollowUsers(@Query("ids") String ids);

    /**
     * Remove the current user as a follower of one or more Spotify artists.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the artists
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/unfollow-artists-users/">Unfollow Artists or Users</a>
     */


    /**
     * Remove the current user as a follower of one or more Spotify artists.
     *
     * @param ids A comma-separated list of the Spotify IDs for the artists
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/unfollow-artists-users/">Unfollow Artists or Users</a>
     */
    @DELETE("/v1/me/following?type=artist")
    Call<Result> unfollowArtists(@Query("ids") String ids);

    /**
     * Check to see if the current user is following one or more other Spotify users.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the users
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/check-current-user-follows/">Check if Current User Follows Artists or Users</a>
     */


    /**
     * Check to see if the current user is following one or more other Spotify users.
     *
     * @param ids A comma-separated list of the Spotify IDs for the users
     * @return An array with boolean values indicating whether the users are followed
     * @see <a href="https://developer.spotify.com/web-api/check-current-user-follows/">Check if Current User Follows Artists or Users</a>
     */
    @GET("/v1/me/following/contains?type=user")
    Call<Boolean[]> isFollowingUsers(@Query("ids") String ids);

    /**
     * Check to see if the current user is following one or more other Spotify artists.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the artists
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/check-current-user-follows/">Check if Current User Follows Artists or Users</a>
     */


    /**
     * Check to see if the current user is following one or more other Spotify artists.
     *
     * @param ids A comma-separated list of the Spotify IDs for the artists
     * @return An array with boolean values indicating whether the artists are followed
     * @see <a href="https://developer.spotify.com/web-api/check-current-user-follows/">Check if Current User Follows Artists or Users</a>
     */
    @GET("/v1/me/following/contains?type=artist")
    Call<Boolean[]> isFollowingArtists(@Query("ids") String ids);

    /**
     * Check to see if one or more Spotify users are following a specified playlist.
     *
     * @param userId     The Spotify user ID of the person who owns the playlist.
     * @param playlistId The Spotify ID of the playlist.
     * @param ids        A comma-separated list of the Spotify IDs for the users
     * @return An array with boolean values indicating whether the playlist is followed by the users
     * @see <a href="https://developer.spotify.com/web-api/check-user-following-playlist/">Check if Users Follow a Playlist</a>
     */
    @GET("/v1/users/{user_id}/playlists/{playlist_id}/followers/contains")
    Call<Boolean[]> areFollowingPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("ids") String ids);

    /**
     * Check to see if one or more Spotify users are following a specified playlist.
     *
     * @param userId     The Spotify user ID of the person who owns the playlist.
     * @param playlistId The Spotify ID of the playlist.
     * @param ids        A comma-separated list of the Spotify IDs for the users
     * @param callback   Callback method.
     * @see <a href="https://developer.spotify.com/web-api/check-user-following-playlist/">Check if Users Follow a Playlist</a>
     */


    /**
     * Get the current user's followed artists.
     *
     * @return Object containing a list of artists that user follows wrapped in a cursor object.
     * @see <a href="https://developer.spotify.com/web-api/get-followed-artists/">Get User's Followed Artists</a>
     */
    @GET("/v1/me/following?type=artist")
    Call<ArtistsCursorPager> getFollowedArtists();

    /**
     * Get the current user's followed artists.
     *
     * @param callback Callback method.
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/get-followed-artists/">Get User's Followed Artists</a>
     */


    /**
     * Get the current user's followed artists.
     *
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/get-followed-artists/">endpoint documentation</a>
     * @return Object containing a list of artists that user follows wrapped in a cursor object.
     * @see <a href="https://developer.spotify.com/web-api/get-followed-artists/">Get User's Followed Artists</a>
     */
    @GET("/v1/me/following?type=artist")
    Call<ArtistsCursorPager> getFollowedArtists(@QueryMap Map<String, Object> options);

    /**
     * Get the current user's followed artists.
     *
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-followed-artists/">endpoint documentation</a>
     * @param callback Callback method.
     * @return An empty result
     * @see <a href="https://developer.spotify.com/web-api/get-followed-artists/">Get User's Followed Artists</a>
     */


    /**********
     * Search *
     **********/

    /**
     * Get Spotify catalog information about tracks that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about tracks that match a keyword string.
     *
     * @param q The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=track")
    Call<TracksPager> searchTracks(@Query("q") String q);

    /**
     * Get Spotify catalog information about tracks that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about tracks that match a keyword string.
     *
     * @param q       The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=track")
    Call<TracksPager> searchTracks(@Query("q") String q, @QueryMap Map<String, Object> options);

    /**
     * Get Spotify catalog information about artists that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about artists that match a keyword string.
     *
     * @param q The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=artist")
    Call<ArtistsPager> searchArtists(@Query("q") String q);

    /**
     * Get Spotify catalog information about artists that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about artists that match a keyword string.
     *
     * @param q       The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=artist")
    Call<ArtistsPager> searchArtists(@Query("q") String q, @QueryMap Map<String, Object> options);

    /**
     * Get Spotify catalog information about albums that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about albums that match a keyword string.
     *
     * @param q The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=album")
    Call<AlbumsPager> searchAlbums(@Query("q") String q);

    /**
     * Get Spotify catalog information about albums that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about albums that match a keyword string.
     *
     * @param q       The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=album")
    Call<AlbumsPager> searchAlbums(@Query("q") String q, @QueryMap Map<String, Object> options);

    /**
     * Get Spotify catalog information about playlists that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param callback Callback method
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about playlists that match a keyword string.
     *
     * @param q The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=playlist")
    Call<PlaylistsPager> searchPlaylists(@Query("q") String q);

    /**
     * Get Spotify catalog information about playlists that match a keyword string.
     *
     * @param q        The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options  Optional parameters. For list of supported parameters see
     *                 <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @param callback Callback method.
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */


    /**
     * Get Spotify catalog information about playlists that match a keyword string.
     *
     * @param q       The search query's keywords (and optional field filters and operators), for example "roadhouse+blues"
     * @param options Optional parameters. For list of supported parameters see
     *                <a href="https://developer.spotify.com/web-api/search-item/">endpoint documentation</a>
     * @return A paginated list of results
     * @see <a href="https://developer.spotify.com/web-api/search-item/">Search for an Item</a>
     */
    @GET("/v1/search?type=playlist")
    Call<PlaylistsPager> searchPlaylists(@Query("q") String q, @QueryMap Map<String, Object> options);

    /******************
     * Audio features *
     ******************/

    /**
     * Get audio features for multiple tracks based on their Spotify IDs.
     *
     * @param ids      A comma-separated list of the Spotify IDs for the tracks. Maximum: 100 IDs
     * @param callback Callback method
     */


    /**
     * Get audio features for multiple tracks based on their Spotify IDs.
     *
     * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 100 IDs
     * @return An object whose key is "audio_features" and whose value is an array of audio features objects.
     */
    @GET("/v1/audio-features")
    Call<AudioFeaturesTracks> getTracksAudioFeatures(@Query("ids") String ids);

    /**
     * Get audio feature information for a single track identified by its unique Spotify ID.
     *
     * @param id       The Spotify ID for the track.
     * @param callback Callback method
     */


    /**
     * Get audio feature information for a single track identified by its unique Spotify ID.
     *
     * @param id The Spotify ID for the track.
     * @return Audio features object
     */
    @GET("/v1/audio-features/{id}")
    Call<AudioFeaturesTrack> getTrackAudioFeatures(@Path("id") String id);

    /*******************
     * Recommendations *
     *******************/

    /**
     * Create a playlist-style listening experience based on seed artists, tracks and genres.
     *
     * @param options Optional parameters. For list of available parameters see
     *                <a href="https://developer.spotify.com/web-api/get-recommendations/">endpoint documentation</a>
     * @return Recommendations response object
     */
    @GET("/v1/recommendations")
    Call<Recommendations> getRecommendations(@QueryMap Map<String, Object> options);

    /**
     * Create a playlist-style listening experience based on seed artists, tracks and genres.
     *
     * @param options  Optional parameters. For list of available parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-recommendations/">endpoint documentation</a>
     * @param callback callback method
     */


    /**
     * Retrieve a list of available genres seed parameter values for recommendations.
     *
     * @return An object whose key is "genres" and whose value is an array of available genres.
     */
    @GET("/v1/recommendations/available-genre-seeds")
    Call<SeedsGenres> getSeedsGenres();

    /**
     * Retrieve a list of available genres seed parameter values for recommendations.
     *
     * @param callback callback method
     */


    /*****************************
     * User Top Artists & Tracks *
     *****************************/

    /**
     * Get the current user’s top artists based on calculated affinity.
     *
     * @return The objects whose response body contains an artists or tracks object.
     * The object in turn contains a paging object of Artists or Tracks
     */
    @GET("/v1/me/top/artists")
    Call<Pager<Artist>> getTopArtists();

    /**
     * Get the current user’s top artists based on calculated affinity.
     *
     * @param callback callback method
     */


    /**
     * Get the current user’s top artists based on calculated affinity.
     *
     * @param options Optional parameters. For list of available parameters see
     *                <a href="https://developer.spotify.com/web-api/get-users-top-artists-and-tracks/">endpoint documentation</a>
     * @return The objects whose response body contains an artists or tracks object.
     * The object in turn contains a paging object of Artists or Tracks
     */
    @GET("/v1/me/top/artists")
    Call<Pager<Artist>> getTopArtists(@QueryMap Map<String, Object> options);

    /**
     * Get the current user’s top artists based on calculated affinity.
     *
     * @param options  Optional parameters. For list of available parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-users-top-artists-and-tracks/">endpoint documentation</a>
     * @param callback Callback method
     */


    /**
     * Get the current user’s top tracks based on calculated affinity.
     *
     * @return The objects whose response body contains an artists or tracks object.
     * The object in turn contains a paging object of Artists or Tracks
     */
    @GET("/v1/me/top/tracks")
    Call<Pager<Track>> getTopTracks();

    /**
     * Get the current user’s top tracks based on calculated affinity.
     *
     * @param callback callback method
     */


    /**
     * Get the current user’s top tracks based on calculated affinity.
     *
     * @param options Optional parameters. For list of available parameters see
     *                <a href="https://developer.spotify.com/web-api/get-users-top-artists-and-tracks/">endpoint documentation</a>
     * @return The objects whose response body contains an artists or tracks object.
     * The object in turn contains a paging object of Artists or Tracks
     */
    @GET("/v1/me/top/tracks")
    Call<Pager<Track>> getTopTracks(@QueryMap Map<String, Object> options);

    /**
     * Get the current user’s top tracks based on calculated affinity.
     *
     * @param options  Optional parameters. For list of available parameters see
     *                 <a href="https://developer.spotify.com/web-api/get-users-top-artists-and-tracks/">endpoint documentation</a>
     * @param callback Callback method
     */


}