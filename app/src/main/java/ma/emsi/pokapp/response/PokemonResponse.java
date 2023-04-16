package ma.emsi.pokapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.emsi.pokapp.models.Pokemon;

@NoArgsConstructor
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PokemonResponse {
    @SerializedName("results")
    private List<Pokemon> pokemonCall = new ArrayList<>();
}
