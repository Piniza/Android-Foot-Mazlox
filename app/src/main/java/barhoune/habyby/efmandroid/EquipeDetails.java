package barhoune.habyby.efmandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import barhoune.habyby.efmandroid.databinding.FragmentEquipeDetailsBinding;
import barhoune.habyby.efmandroid.models.team.Team;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EquipeDetails extends Fragment {
    private static final String TAG = EquipeDetails.class.getSimpleName();

    private static final String BASE_URL = "https://api.football-data.org/v4/";
    private static final String API_KEY = "07717fde5b0f4d3f85999c7957eff184";
    FragmentEquipeDetailsBinding binding;
    TextView coachname,coachnationality,name,abrev,tiran,annee,addresse,couleur;
    ImageView logoteam;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= FragmentEquipeDetailsBinding.inflate(inflater, container, false);
        int idteam=EquipeDetailsArgs.fromBundle(getArguments()).getTeamId();

        name=binding.tvname;
        abrev=binding.tvtla;
        tiran=binding.tvvenue;
        annee=binding.tvfounded;
        addresse=binding.tvaddress;
        couleur= binding.tvcolors;
        logoteam=binding.imageView2;
        coachname=binding.tvcoachname;
        coachnationality=binding.tvcoachnat;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService apiService = retrofit.create(FootballApiService.class);

        Call<Team> call = apiService.getTeam(idteam,API_KEY);

        call.enqueue(new Callback<Team>() {

            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    Team teams = response.body();
                    name.setText(teams.getName());
                    abrev.setText(teams.getTla());
                    tiran.setText(teams.getTiran());
                    annee.setText(teams.getAnnee());
                    addresse.setText(teams.getAddress());
                    couleur.setText(teams.getCouleurs());
                    coachname.setText(teams.getCoachs().getCoachname());
                    coachnationality.setText(teams.getCoachs().getNationality());
                    Picasso.get().load(teams.getCrestUrl()).into(logoteam);

                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());

            }


        });

        return binding.getRoot();}}