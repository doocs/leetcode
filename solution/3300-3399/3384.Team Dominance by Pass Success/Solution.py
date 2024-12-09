import pandas as pd


def calculate_team_dominance(teams: pd.DataFrame, passes: pd.DataFrame) -> pd.DataFrame:
    passes_with_teams = passes.merge(
        teams, left_on="pass_from", right_on="player_id", suffixes=("", "_team_from")
    ).merge(
        teams,
        left_on="pass_to",
        right_on="player_id",
        suffixes=("_team_from", "_team_to"),
    )
    passes_with_teams["half_number"] = passes_with_teams["time_stamp"].apply(
        lambda x: 1 if x <= "45:00" else 2
    )
    passes_with_teams["dominance"] = passes_with_teams.apply(
        lambda row: 1 if row["team_name_team_from"] == row["team_name_team_to"] else -1,
        axis=1,
    )
    result = (
        passes_with_teams.groupby(["team_name_team_from", "half_number"])["dominance"]
        .sum()
        .reset_index()
    )
    result.columns = ["team_name", "half_number", "dominance"]
    result = result.sort_values(by=["team_name", "half_number"])
    return result
