package ma.emsi.pokapp.models;

import com.google.gson.annotations.SerializedName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Pokemon {
    private String name;

    private String url;

    @NoArgsConstructor
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    public static class PokemonInfo {
        @SerializedName("base_experience")
        private int experience;

        private int height;

        private int weight;

        private int order;
    }

    @NoArgsConstructor
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    public static class PokemonMove {
        private int power;

        @SerializedName("pp")
        private int powerPoints;
    }
}
