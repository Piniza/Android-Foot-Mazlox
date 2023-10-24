package barhoune.habyby.efmandroid;

import barhoune.habyby.efmandroid.models.competition.Competitions;
import barhoune.habyby.efmandroid.models.match.Match;
import barhoune.habyby.efmandroid.models.match.Matches;
import barhoune.habyby.efmandroid.models.standing.Standings;
import barhoune.habyby.efmandroid.models.team.Team;
import barhoune.habyby.efmandroid.models.team.Teams;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FootballApiService {
    @GET("competitions")
    Call<Competitions> getCompetition(
            @Header("X-Auth-Token") String apiKey
    );

    @GET("competitions/{leagueId}/teams")
    Call<Teams> getTeams(
            @Path("leagueId") String leagueId,
            @Header("X-Auth-Token") String apiKey
    );

    @GET("competitions/{leagueId}/matches")
    Call<Matches> getMatches(
            @Path("leagueId") String leagueId,
            @Header("X-Auth-Token") String apiKey
    );
    @GET("matches")
    Call<Matches> getTodayMatches(
            @Header("X-Auth-Token") String apiKey
    );
    @GET("competitions/{leagueId}/standings")
    Call<Standings> getStandings(
            @Path("leagueId") String leagueId,
            @Header("X-Auth-Token") String apiKey
    );
    @GET("teams/{id}")
    Call<Team> getTeam(
            @Path("id") int leagueId,
            @Header("X-Auth-Token") String apiKey
    );
    @GET("matches/{id}")
    Call<Match> getMatch(
            @Path("id") int leagueId,
            @Header("X-Auth-Token") String apiKey
    );

}
