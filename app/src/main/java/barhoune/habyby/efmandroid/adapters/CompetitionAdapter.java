package barhoune.habyby.efmandroid.adapters;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import barhoune.habyby.efmandroid.CompetitionFragmentDirections;

import barhoune.habyby.efmandroid.ListItemClickListener;
import barhoune.habyby.efmandroid.MatchesFragmentDirections;
import barhoune.habyby.efmandroid.R;
import barhoune.habyby.efmandroid.models.competition.Competition;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.ViewHolder> {
    private List<Competition> competitions = new ArrayList<>();
    public ListItemClickListener listener;
    public static String cod;

    public void setCompetitions(List<Competition> competitions,ListItemClickListener listener) {
        this.competitions = competitions;
        this.listener=listener;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Competition competition = competitions.get(position);

        holder.tvName.setText(competition.getCompetitionName());
        Picasso.get().load(competition.getCrestUrl()).error(R.drawable.competition).into(holder.ivLogo);

        holder.itemView.setOnClickListener(view -> {
        cod=competition.getCompetitionCode();
        CompetitionFragmentDirections.ActionCompetitionatToEquipesFragment action;
        action= CompetitionFragmentDirections.actionCompetitionatToEquipesFragment(competition.getCompetitionCode());
        Navigation.findNavController(view).navigate(action);
        });

    }

    @Override
    public int getItemCount() {
        return competitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {



        TextView tvName;
        ImageView ivLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.textView);
            ivLogo = itemView.findViewById(R.id.imageView);
        }
        public ViewHolder(View itemView, final ListItemClickListener listener) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Competition competition = competitions.get(position);
                        String competitionCode = competition.getCompetitionCode();
                        listener.onCompetitionClick(competitionCode);
                    }
                }
            });
        }

    }
}
