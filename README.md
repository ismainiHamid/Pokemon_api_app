# App Pokémon (Android)
We retrieved Pokémon data using PokeAPI (https://pokeapi.co/), Retrofit (https://square.github.io/retrofit/)

#### Class "ApiConnection"

```java
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
```

# Screenshots
<div align="center">
    <img src="screenshots/PokAPI-1.jpg" width="300" />
    &nbsp; &nbsp; &nbsp; &nbsp;
    <img src="screenshots/PokAPI-2.jpg" width="300" />
</div>


