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
import barhoune.habyby.efmandroid.R;
import barhoune.habyby.efmandroid.models.team.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Team> mTeams= new ArrayList<>();
    public ListTeamsClickListener listener;



    public void setTeams(List<Team> teams, ListTeamsClickListener listener) {
        mTeams = teams;
        this.listener=listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipes, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team = mTeams.get(position);

        holder.tvName.setText(team.getName());
        Picasso.get().load(team.getCrestUrl()).resize(100,100).error(R.drawable.crest).into(holder.ivLogo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTeamClick(team.getID());
            }
        });
        holder.itemView.setOnClickListener(view -> { EquipesFragmentDirections.ActionEquipesFragmentToEquipeDetails action;
        action= EquipesFragmentDirections.actionEquipesFragmentToEquipeDetails(team.getID());
        Navigation.findNavController(view).navigate(action); });
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvequipe);
            ivLogo = itemView.findViewById(R.id.imgequipe);
        }
        public ViewHolder(View itemView, final ListTeamsClickListener listener) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Team team = mTeams.get(position);
                        int teamId = team.getID();
                        listener.onTeamClick(teamId);
                    }
                }
            });
        }
    }


}