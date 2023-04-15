package ma.emsi.pokapp.services;

import ma.emsi.pokapp.models.PokemonInfo;
import ma.emsi.pokapp.models.PokemonMove;
import ma.emsi.pokapp.response.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPokemonServiceAPI {
    @GET("pokemon")
    Call<PokemonResponse> pokemonResponseCall(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonInfo> pokemonInfoCall(@Path("id") int id);

    @GET("move/{id}")
    Call<PokemonMove> pokemonMoveCall(@Path("id") int id);
}
