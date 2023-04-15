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

### Class ou models ""

```java
@NoArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Pokemon {
    private String name;

    private String url;
}

```

```java
@NoArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PokemonInfo {
    @SerializedName("base_experience")
    private int experience;

    private int height;

    private int weight;

    private int order;
}
```

```java
@NoArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PokemonMove {
    private int power;

    @SerializedName("pp")
    private int powerPoints;
}
```

# Screenshots
<div align="center">
    <img src="screenshots/PokAPI-1.jpg" width="300" />
    &nbsp; &nbsp; &nbsp; &nbsp;
    <img src="screenshots/PokAPI-2.jpg" width="300" />
</div>


