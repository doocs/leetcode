import pandas as pd


def snap_analysis(activities: pd.DataFrame, age: pd.DataFrame) -> pd.DataFrame:
    merged_df = pd.merge(activities, age, on="user_id")
    total_time_per_age_activity = (
        merged_df.groupby(["age_bucket", "activity_type"])["time_spent"]
        .sum()
        .reset_index()
    )
    pivot_df = total_time_per_age_activity.pivot(
        index="age_bucket", columns="activity_type", values="time_spent"
    ).reset_index()
    pivot_df = pivot_df.fillna(0)
    pivot_df["send_perc"] = round(
        100 * pivot_df["send"] / (pivot_df["send"] + pivot_df["open"]), 2
    )
    pivot_df["open_perc"] = round(
        100 * pivot_df["open"] / (pivot_df["send"] + pivot_df["open"]), 2
    )
    return pivot_df[["age_bucket", "send_perc", "open_perc"]]
