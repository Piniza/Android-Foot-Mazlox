package barhoune.habyby.efmandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import barhoune.habyby.efmandroid.ListTeamsClickListener;
import barhoune.habyby.efmandroid.R;
import barhoune.habyby.efmandroid.models.standing.Table;

public class StandingAdapter extends RecyclerView.Adapter<StandingAdapter.ViewHolder> {

    private List<Table> list=new ArrayList<>();
    public ListTeamsClickListener listener;



    public void setStandings(List<Table> standings) {
        this.list = standings;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(position);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTeam;
        TextView tvPos;
        TextView tvPlayed;
        TextView tvWins;
        TextView tvDraws;
        TextView tvLosses;
        TextView tvPoints;
        ImageView imgTeam;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPos = itemView.findViewById(R.id.tvRank);
            tvTeam = itemView.findViewById(R.id.tvRankTeamName);
            imgTeam = itemView.findViewById(R.id.imgRankTeam);
            tvPlayed = itemView.findViewById(R.id.tvRankPlayed);
            tvWins = itemView.findViewById(R.id.tvRankWin);
            tvDraws = itemView.findViewById(R.id.tvRankDraw);
            tvLosses = itemView.findViewById(R.id.tvRankLose);
            tvPoints = itemView.findViewById(R.id.tvRankPoints);





        }
        public ViewHolder(View itemView, final ListTeamsClickListener listener) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bind(int position) {
            tvPos.setText(String.valueOf(list.get(position).getRank()));
            tvTeam.setText(list.get(position).getTeam().getName());
            System.out.println(list.get(position).getTeam().getName());
            tvPoints.setText(String.valueOf(list.get(position).rankpoints));
            tvDraws.setText(String.valueOf(list.get(position).draw));
            tvWins.setText(String.valueOf(list.get(position).wins));
            tvPlayed.setText(String.valueOf(list.get(position).playedGames));
            tvLosses.setText(String.valueOf(list.get(position).lost));
            Picasso.get().load(list.get(position).getTeam().getCrestUrl()).resize(100,100).error(R.drawable.crest).into(imgTeam);


        }
    }


}