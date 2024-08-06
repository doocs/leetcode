import pandas as pd


def calculate_team_standings(team_stats: pd.DataFrame) -> pd.DataFrame:
    team_stats["points"] = team_stats["wins"] * 3 + team_stats["draws"]
    team_stats["position"] = team_stats["points"].rank(method="min", ascending=False)
    team_stats = team_stats.sort_values(
        by=["points", "team_name"], ascending=[False, True]
    )
    return team_stats[["team_id", "team_name", "points", "position"]]
