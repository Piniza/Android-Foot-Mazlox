package barhoune.habyby.efmandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import barhoune.habyby.efmandroid.EquipesFragmentDirections;
import barhoune.habyby.efmandroid.ListTeamsClickListener;
import barhoune.habyby.efmandroid.MatchesFragmentDirections;
import barhoune.habyby.efmandroid.R;
import barhoune.habyby.efmandroid.models.match.Match;
import barhoune.habyby.efmandroid.models.match.Matches;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private List<Match> mMatches= new ArrayList<>();
    public ListTeamsClickListener listener;



    public void setmMatches(List<Match> matchs,ListTeamsClickListener listener) {
        mMatches = matchs;
        this.listener=listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = mMatches.get(position);

            holder.tvHome.setText(match.getMatchHomeTeam().getHomeTeamName());
            holder.tvHomeScr.setText(match.getMatchScore().getFulltime().getHomescore());
            holder.tvAwayScr.setText(match.getMatchScore().getFulltime().getAwayscore());
            holder.tvAway.setText(match.getMatchAwayTeam().getAwayTeamName());
            holder.tvDate.setText(match.getMatchDate().toString());
            Picasso.get().load(match.getMatchAwayTeam().getAwayTeamCrest()).error(R.drawable.awaycrest).resize(50,50).into(holder.imgAway);
            Picasso.get().load(match.getMatchHomeTeam().getHomeTeamCrest()).error(R.drawable.homecrest).resize(50,50).into(holder.imgHome);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTeamClick(match.getMatchId());
            }
            });
          holder.itemView.setOnClickListener(view -> {
              barhoune.habyby.efmandroid.MatchesFragmentDirections.ActionMatchesFragmentToMatchDetails action;
              action= MatchesFragmentDirections.actionMatchesFragmentToMatchDetails(match.getMatchId());
              Navigation.findNavController(view).navigate(action); });

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTeamClick(match.getID());
            }
        });*/
    }




    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAway;
        TextView tvAwayScr;
        TextView tvHome;
        TextView tvHomeScr;
        TextView tvDate;
        ImageView imgHome;
        ImageView imgAway;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHome= itemView.findViewById(R.id.homeimg);
            imgAway= itemView.findViewById(R.id.awayimg);
            tvHome= itemView.findViewById(R.id.tvhomename);
            tvAway = itemView.findViewById(R.id.tvawayname);
            tvAwayScr= itemView.findViewById(R.id.tvawayscr);
            tvHomeScr= itemView.findViewById(R.id.tvhomscr);
            tvDate= itemView.findViewById(R.id.tvmatchdate);



        }
        public ViewHolder(View itemView, final ListTeamsClickListener listener) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Match match = mMatches.get(position);
                        int matchId = match.getMatchId() ;
                        listener.onTeamClick(matchId);
                    }
                }
            });
        }
    }


}