package ma.emsi.pokapp.connection;

import java.util.Objects;

import ma.emsi.pokapp.services.IPokemonServiceAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    private static Retrofit retrofit;
    private static IPokemonServiceAPI pokemonServiceAPI;

    private ApiConnection(){
        try {
            retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            pokemonServiceAPI = retrofit.create(IPokemonServiceAPI.class);
        }catch (Exception exception){ exception.printStackTrace(); }
    }

    public static synchronized IPokemonServiceAPI getInstance(){
        if(Objects.isNull(retrofit) || Objects.isNull(pokemonServiceAPI)) new ApiConnection();
        return pokemonServiceAPI;
    }
}
