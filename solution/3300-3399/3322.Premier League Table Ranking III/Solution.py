import pandas as pd


def process_team_standings(season_stats: pd.DataFrame) -> pd.DataFrame:
    season_stats["points"] = season_stats["wins"] * 3 + season_stats["draws"]
    season_stats["goal_difference"] = (
        season_stats["goals_for"] - season_stats["goals_against"]
    )

    season_stats = season_stats.sort_values(
        ["season_id", "points", "goal_difference", "team_name"],
        ascending=[True, False, False, True],
    )

    season_stats["position"] = season_stats.groupby("season_id").cumcount() + 1

    return season_stats[
        ["season_id", "team_id", "team_name", "points", "goal_difference", "position"]
    ]
