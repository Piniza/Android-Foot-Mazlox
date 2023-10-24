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
import barhoune.habyby.efmandroid.models.competition.Competitions;
import barhoune.habyby.efmandroid.models.competition.Competition;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import barhoune.habyby.efmandroid.databinding.FragmentCompetitionBinding;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class CompetitionFragment extends Fragment implements ListItemClickListener {
    private static final String BASE_URL = "https://api.football-data.org/v4/";
    private static final String API_KEY = "621b6c9116bf456193040a9717fe2051";
    private RecyclerView mRecyclerView;
    private CompetitionAdapter mAdapter;
    FragmentCompetitionBinding binding;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= FragmentCompetitionBinding.inflate(inflater, container, false);
        mRecyclerView = binding.rvcompetitions1;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CompetitionAdapter();
        mRecyclerView.setAdapter(mAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService apiService = retrofit.create(FootballApiService.class);

        Call<Competitions> call = apiService.getCompetition(API_KEY);
        // Inflate the layout for this fragment
        call.enqueue(new Callback<Competitions>() {
            @Override
            public void onResponse(Call<Competitions> call, Response<Competitions> response) {
                if (response.isSuccessful()) {
                    Competitions listCompetitions = response.body();
                    if (listCompetitions != null) {
                        List<Competition> competitions = listCompetitions.getCompetitionList();
                        mAdapter.setCompetitions(competitions, CompetitionFragment.this);
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
        return binding.getRoot();
    }

    @Override
    public void onCompetitionClick(String competitionCode) {

    }
}