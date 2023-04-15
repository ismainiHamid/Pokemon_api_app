package ma.emsi.pokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import ma.emsi.pokapp.adapter.ListPokemonAdapter;
import ma.emsi.pokapp.connection.ApiConnection;
import ma.emsi.pokapp.response.PokemonResponse;
import ma.emsi.pokapp.tools.SpacingBetweenItems;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "POKEMON";
    private static int offset = 0;
    private static boolean status = false;
    private ListPokemonAdapter listPokemonAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        this.listPokemonAdapter = new ListPokemonAdapter(this);

        this.recyclerView = (RecyclerView) findViewById(R.id.listPokemonView);
        this.recyclerView.addItemDecoration(new SpacingBetweenItems(20, 20, 20, 20));
        this.recyclerView.setAdapter(this.listPokemonAdapter);
        this.recyclerView.setHasFixedSize(true);

        this.onScrolled(this.recyclerView, 2);
        this.onCharge(offset);
    }

    private void onCharge(int offset){
        ApiConnection.getInstance().pokemonResponseCall(offset, 20).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                status = true;
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.message());
                    return;
                }
                listPokemonAdapter.copyListPokemon(response.body().getPokemonCall());
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                status = true;
                Log.i(TAG, "Error : " + t.getMessage());
            }
        });
    }

    private void onScrolled(RecyclerView recyclerView, int item){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, item);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                     int visibleItems = gridLayoutManager.getItemCount();
                     int totalItems = gridLayoutManager.getItemCount();
                     int pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if(status){
                        if(visibleItems + pastVisibleItems >= totalItems){
                            status = false;
                            offset += 20;
                            onCharge(offset);
                        }
                    }
                }
            }
        });
    }

}