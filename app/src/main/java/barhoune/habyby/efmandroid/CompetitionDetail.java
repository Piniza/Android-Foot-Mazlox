package barhoune.habyby.efmandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import barhoune.habyby.efmandroid.databinding.ActivityCompetitionDetailBinding;
import android.os.Bundle;
import android.view.View;

public class CompetitionDetail extends AppCompatActivity {
ActivityCompetitionDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCompetitionDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        final NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);
        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if (navDestination.getId()==R.id.competitionat || navDestination.getId()==R.id.todayMatchesFragment){
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
                binding.bottomNavigationView.getMenu().findItem(R.id.equipesFragment).setVisible(false);
                binding.bottomNavigationView.getMenu().findItem(R.id.classementFragment).setVisible(false);
                binding.bottomNavigationView.getMenu().findItem(R.id.matchesFragment).setVisible(false);
                binding.bottomNavigationView.getMenu().findItem(R.id.competitionat).setVisible(true);
                binding.bottomNavigationView.getMenu().findItem(R.id.todayMatchesFragment).setVisible(true);

            } else if (navDestination.getId() == R.id.matchDetails) {
                binding.bottomNavigationView.setVisibility(View.GONE);
            }
            //   binding.bottomNavigationView.setVisibility(View.GONE);
            else {
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
                binding.bottomNavigationView.getMenu().findItem(R.id.equipesFragment).setVisible(true);
                binding.bottomNavigationView.getMenu().findItem(R.id.classementFragment).setVisible(true);
                binding.bottomNavigationView.getMenu().findItem(R.id.matchesFragment).setVisible(true);
                binding.bottomNavigationView.getMenu().findItem(R.id.competitionat).setVisible(false);
                binding.bottomNavigationView.getMenu().findItem(R.id.todayMatchesFragment).setVisible(false);
            }
               // binding.bottomNavigationView.setVisibility(View.VISIBLE);
        });
    }
}