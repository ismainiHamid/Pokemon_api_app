package ma.emsi.pokapp.models;

import com.google.gson.annotations.SerializedName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PokemonMove {
    private int power;

    @SerializedName("pp")
    private int powerPoints;
}
