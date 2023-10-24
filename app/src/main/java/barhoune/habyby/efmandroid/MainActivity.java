package barhoune.habyby.efmandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import barhoune.habyby.efmandroid.adapters.CompetitionAdapter;
import barhoune.habyby.efmandroid.models.competition.Competition;
import barhoune.habyby.efmandroid.models.competition.Competitions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "https://api.football-data.org/v4/";
    private static final String API_KEY = "621b6c9116bf456193040a9717fe2051";


    private RecyclerView mRecyclerView;
    private CompetitionAdapter mAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.rvcompetitions1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CompetitionAdapter();
        mRecyclerView.setAdapter(mAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService apiService = retrofit.create(FootballApiService.class);

        Call<Competitions> call = apiService.getCompetition(API_KEY);

        call.enqueue(new Callback<Competitions>() {
            @Override
            public void onResponse(Call<Competitions> call, Response<Competitions> response) {
                if (response.isSuccessful()) {
                    Competitions listCompetitions = response.body();
                    if (listCompetitions != null) {
                        List<Competition> competitions = listCompetitions.getCompetitionList();
                        mAdapter.setCompetitions(competitions,MainActivity.this);
                    }
                } else {
                    Log.e("MainActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Competitions> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }

        });

    }

    @Override
    public void onCompetitionClick(String competitionCode) {
        Intent intent = new Intent(MainActivity.this, CompetitionDetail.class);
        intent.putExtra("competitionCode", competitionCode);
        startActivity(intent);
    }
}