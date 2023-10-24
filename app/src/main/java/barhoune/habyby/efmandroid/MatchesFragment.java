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
import barhoune.habyby.efmandroid.adapters.MatchAdapter;
import barhoune.habyby.efmandroid.databinding.FragmentMatchesBinding;
import barhoune.habyby.efmandroid.models.match.Match;
import barhoune.habyby.efmandroid.models.match.Matches;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MatchesFragment extends Fragment implements ListTeamsClickListener {




    private static final String TAG = MatchesFragment.class.getSimpleName();
    private static final String BASE_URL = "https://api.football-data.org/v4/";
    private static final String API_KEY = "07717fde5b0f4d3f85999c7957eff184";

    private FragmentMatchesBinding binding;
    private RecyclerView mRecyclerView;
    private MatchAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= FragmentMatchesBinding.inflate(inflater, container, false);

        mRecyclerView = binding.rvmatch;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MatchAdapter();
        mRecyclerView.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService apiService = retrofit.create(FootballApiService.class);

            Call<Matches> call = apiService.getMatches(CompetitionAdapter.cod,API_KEY);

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                if (response.isSuccessful()) {
                    Matches listMatch = response.body();
                    if (listMatch != null) {
                        List<Match> matches = listMatch.getMatchList();
                        mAdapter.setmMatches(matches, MatchesFragment.this);
                    }
                } else {
                    Log.e("MainActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }

        });
        return binding.getRoot();


    }


    @Override
    public void onTeamClick(int teamId) {

    }
}