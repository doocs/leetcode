import pandas as pd


def analyze_subscription_conversion(user_activity: pd.DataFrame) -> pd.DataFrame:
    df = user_activity[user_activity["activity_type"] != "cancelled"]

    df_grouped = (
        df.groupby(["user_id", "activity_type"])["activity_duration"]
        .mean()
        .add(0.0001)
        .round(2)
        .reset_index()
    )

    df_free_trial = (
        df_grouped[df_grouped["activity_type"] == "free_trial"]
        .rename(columns={"activity_duration": "trial_avg_duration"})
        .drop(columns=["activity_type"])
    )

    df_paid = (
        df_grouped[df_grouped["activity_type"] == "paid"]
        .rename(columns={"activity_duration": "paid_avg_duration"})
        .drop(columns=["activity_type"])
    )

    result = df_free_trial.merge(df_paid, on="user_id", how="inner").sort_values(
        "user_id"
    )

    return result
