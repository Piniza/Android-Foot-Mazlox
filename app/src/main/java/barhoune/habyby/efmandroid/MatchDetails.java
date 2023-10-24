package barhoune.habyby.efmandroid;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import barhoune.habyby.efmandroid.databinding.FragmentMatchDetailsBinding;
import barhoune.habyby.efmandroid.models.match.Match;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MatchDetails  extends Fragment {
    private static final String TAG = EquipeDetails.class.getSimpleName();

    private static final String BASE_URL = "https://api.football-data.org/v4/";
    private static final String API_KEY = "07717fde5b0f4d3f85999c7957eff184";
    FragmentMatchDetailsBinding binding;
    TextView tvmatchhome,tvmatchaway,tvscrhome,tvscraway,tvdate, tvmatchstage, tvstatus,tiran;
    ImageView AwayteamLogo,HomeTeamLogo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= FragmentMatchDetailsBinding.inflate(inflater, container, false);
        int idMatch=MatchDetailsArgs.fromBundle(getArguments()).getMatchID();
        tvmatchhome =binding.textmatchhome;
        tvmatchaway =binding.tvmatchAway;
        tiran=binding.tvvenue;
        tvmatchstage =binding.tvstage;
        tvstatus =binding.tvstatus;
        tvscrhome=binding.tVhomescore;
        tvscraway=binding.tVawayscore;
        HomeTeamLogo=binding.imgHome;
        AwayteamLogo=binding.imgHome2;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService apiService = retrofit.create(FootballApiService.class);

        Call<Match> call = apiService.getMatch(idMatch,API_KEY);

        call.enqueue(new Callback<Match>() {

            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                if (response.isSuccessful()) {
                    Match match = response.body();
                    tvmatchaway.setText(match.getMatchAwayTeam().getAtName());
                    tvmatchhome.setText(match.getMatchHomeTeam().getHtName());
                    tiran.setText(match.getMatchVenue());
                    tvstatus.setText(match.getStatus());
                    tvmatchstage.setText(match.getMatchStage());
                    tvscrhome.setText(match.getMatchScore().getFulltime().getHomescore());
                    tvscraway.setText(match.getMatchScore().getFulltime().getAwayscore());
                    Picasso.get().load(match.getMatchHomeTeam().getHomeTeamCrest()).into(HomeTeamLogo);
                    Picasso.get().load(match.getMatchAwayTeam().getAwayTeamCrest()).into(AwayteamLogo);

                }
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());

            }


        });

        return binding.getRoot();}}