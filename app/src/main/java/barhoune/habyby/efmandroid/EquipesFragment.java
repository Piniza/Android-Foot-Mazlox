package barhoune.habyby.efmandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import barhoune.habyby.efmandroid.adapters.CompetitionAdapter;
import barhoune.habyby.efmandroid.databinding.FragmentEquipesBinding;
import barhoune.habyby.efmandroid.adapters.TeamAdapter;
import barhoune.habyby.efmandroid.models.team.Team;
import barhoune.habyby.efmandroid.models.team.Teams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EquipesFragment extends Fragment implements ListTeamsClickListener{




        private static final String TAG = EquipesFragment.class.getSimpleName();
        private static final String BASE_URL = "https://api.football-data.org/v4/";
        private static final String API_KEY = "07717fde5b0f4d3f85999c7957eff184";

        private FragmentEquipesBinding binding;
        private RecyclerView mRecyclerView;
        private TeamAdapter mAdapter;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                    Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           binding= FragmentEquipesBinding.inflate(inflater, container, false);

            mRecyclerView = binding.rvequipe;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new TeamAdapter();
            mRecyclerView.setAdapter(mAdapter);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            FootballApiService apiService = retrofit.create(FootballApiService.class);

            Call<Teams> call = apiService.getTeams(CompetitionAdapter.cod,API_KEY);

            call.enqueue(new Callback<Teams>() {
                @Override
                public void onResponse(Call<Teams> call, Response<Teams> response) {
                    if (response.isSuccessful()) {
                        Teams listTeams = response.body();
                        if (listTeams != null) {
                            List<Team> teams = listTeams.getTeamsList();
                            mAdapter.setTeams(teams, EquipesFragment.this);
                        }
                    } else {
                        Log.e("MainActivity", "Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Teams> call, Throwable t) {
                    Log.e("MainActivity", "Error: " + t.getMessage());
                }

            });
           return binding.getRoot();


        }

        @Override
        public void onTeamClick(int teamId) {
          /*  Intent intent = new Intent(this, EquipeDetails.class);
            intent.putExtra("teamid", teamId);
            startActivity(intent);*/
        }
    }