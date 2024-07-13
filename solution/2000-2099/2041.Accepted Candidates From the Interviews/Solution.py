import pandas as pd


def accepted_candidates(candidates: pd.DataFrame, rounds: pd.DataFrame) -> pd.DataFrame:
    merged_df = pd.merge(candidates, rounds, on="interview_id")
    filtered_df = merged_df[merged_df["years_of_exp"] >= 2]
    grouped_df = filtered_df.groupby("candidate_id").agg({"score": "sum"})
    return grouped_df[grouped_df["score"] > 15].reset_index()[["candidate_id"]]
