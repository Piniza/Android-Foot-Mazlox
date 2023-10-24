package barhoune.habyby.efmandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import barhoune.habyby.efmandroid.adapters.CompetitionAdapter;
import barhoune.habyby.efmandroid.adapters.StandingAdapter;
import barhoune.habyby.efmandroid.databinding.FragmentClassementBinding;
import barhoune.habyby.efmandroid.models.standing.Standing;
import barhoune.habyby.efmandroid.models.standing.Standings;
import barhoune.habyby.efmandroid.models.standing.Table;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassementFragment extends Fragment implements ListTeamsClickListener {




    private static final String TAG = ClassementFragment.class.getSimpleName();
    private static final String BASE_URL = "https://api.football-data.org/v4/";
    private static final String API_KEY = "07717fde5b0f4d3f85999c7957eff184";

    private FragmentClassementBinding binding;
    private RecyclerView mRecyclerView;
    private StandingAdapter sAdapter;
    private List<Standing> standingList;
    private List<Table> tableList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= FragmentClassementBinding.inflate(inflater, container, false);

        mRecyclerView = binding.rvLeagueTable;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sAdapter = new StandingAdapter();
        mRecyclerView.setAdapter(sAdapter);
        //String competitionCode = MatchesFragmentArgs.fromBundle(getArguments()).getCodeCompetition();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService apiService = retrofit.create(FootballApiService.class);

        Call<Standings> call = apiService.getStandings(CompetitionAdapter.cod,API_KEY);

        call.enqueue(new Callback<Standings>() {
            @Override
            public void onResponse(Call<Standings> call, Response<Standings> response) {
                if (response.isSuccessful()) {
                    final Standings res = response.body();
                    if (res != null) {
                        standingList = new ArrayList<>(res.getStandingList());
                        tableList = new ArrayList<>(standingList.get(0).getTable());
                        sAdapter.setStandings(tableList);
                    }
                } else {
                    Log.e("MainActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Standings> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }

        });
        return binding.getRoot();


    }


    @Override
    public void onTeamClick(int teamId) {

    }
}