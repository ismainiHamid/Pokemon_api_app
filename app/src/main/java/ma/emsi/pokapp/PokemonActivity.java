package ma.emsi.pokapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import ma.emsi.pokapp.connection.ApiConnection;
import ma.emsi.pokapp.models.PokemonInfo;
import ma.emsi.pokapp.models.PokemonMove;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {
    private static String TAG = "POKEMON";
    private Intent intent;
    private ProgressBar progressPower, progressExperience, progressHeight, progressWeight;
    private TextView textName, textOrder, textPowerPoints, textPower, textExperience, textHeight, textWeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        this.intent = getIntent();

        this.textName = findViewById(R.id.textName);
        this.textOrder = findViewById(R.id.textOrder);
        this.textPowerPoints = findViewById(R.id.textPowerPoints);
        this.textPower = findViewById(R.id.textPower);
        this.textExperience = findViewById(R.id.textExperience);
        this.textHeight = findViewById(R.id.textHeight);
        this.textWeight = findViewById(R.id.textWeight);

        this.progressPower = (ProgressBar) findViewById(R.id.progressPower);
        this.progressExperience = findViewById(R.id.progressExperience);
        this.progressHeight = findViewById(R.id.progressHeight);
        this.progressWeight = findViewById(R.id.progressWeight);

        int id = Integer.parseInt(intent.getStringExtra("id"));

        Picasso.get().load(intent.getStringExtra("imageUrl")).into((ImageView) findViewById(R.id.mainImage));
        this.textName.setText("#" + id + "." + intent.getStringExtra("name"));

        PokemonMove pokemonMove = null;

        ApiConnection.getInstance().pokemonMoveCall(id).enqueue(new Callback<PokemonMove>() {
            @Override
            public void onResponse(Call<PokemonMove> call, Response<PokemonMove> response) {
                if(response.isSuccessful()) {
                    PokemonMove pokemonMove = response.body();

                    textPowerPoints.setText(String.format("%01d", pokemonMove.getPowerPoints()));

                    ObjectAnimator.ofInt(progressPower, "progress", 1, pokemonMove.getPower()).setDuration(500).start();
                    textPower.setText("Power : " + pokemonMove.getPower() + "/" + progressPower.getMax());
                }
            }

            @Override
            public void onFailure(Call<PokemonMove> call, Throwable t) { Log.i(TAG, "Error : " + t.getMessage()); }
        });

        ApiConnection.getInstance().pokemonInfoCall(id).enqueue(new Callback<PokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if(response.isSuccessful()) {
                    PokemonInfo pokemonInfo = response.body();

                    textOrder.setText(String.format("%03d", pokemonInfo.getOrder()));

                    ObjectAnimator.ofInt(progressExperience, "progress", 1, pokemonInfo.getExperience()).setDuration(500).start();
                    textExperience.setText("Experience : " + pokemonInfo.getExperience() + "/" + progressExperience.getMax());

                    ObjectAnimator.ofInt(progressHeight, "progress", 1, pokemonInfo.getHeight()).setDuration(500).start();
                    textHeight.setText("Height : " + pokemonInfo.getHeight() + "/" + progressHeight.getMax());

                    ObjectAnimator.ofInt(progressWeight, "progress", 1, pokemonInfo.getWeight()).setDuration(500).start();
                    textWeight.setText("Weight : " + pokemonInfo.getWeight() + "/" + progressWeight.getMax());
                }
            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable t) { Log.i(TAG, "Error : " + t.getMessage()); }
        });
    }
}
