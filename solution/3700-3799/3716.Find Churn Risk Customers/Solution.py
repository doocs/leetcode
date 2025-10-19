import pandas as pd


def find_churn_risk_customers(subscription_events: pd.DataFrame) -> pd.DataFrame:
    subscription_events["event_date"] = pd.to_datetime(
        subscription_events["event_date"]
    )
    subscription_events = subscription_events.sort_values(
        ["user_id", "event_date", "event_id"]
    )
    last_events = (
        subscription_events.groupby("user_id")
        .tail(1)[["user_id", "event_type", "plan_name", "monthly_amount"]]
        .rename(
            columns={
                "event_type": "last_event_type",
                "plan_name": "current_plan",
                "monthly_amount": "current_monthly_amount",
            }
        )
    )

    agg_df = (
        subscription_events.groupby("user_id")
        .agg(
            start_date=("event_date", "min"),
            last_event_date=("event_date", "max"),
            max_historical_amount=("monthly_amount", "max"),
            downgrade_count=("event_type", lambda x: (x == "downgrade").sum()),
        )
        .reset_index()
    )

    merged = pd.merge(agg_df, last_events, on="user_id", how="inner")
    merged["days_as_subscriber"] = (
        merged["last_event_date"] - merged["start_date"]
    ).dt.days

    result = merged[
        (merged["last_event_type"] != "cancel")
        & (merged["downgrade_count"] >= 1)
        & (merged["current_monthly_amount"] < 0.5 * merged["max_historical_amount"])
        & (merged["days_as_subscriber"] >= 60)
    ][
        [
            "user_id",
            "current_plan",
            "current_monthly_amount",
            "max_historical_amount",
            "days_as_subscriber",
        ]
    ]

    result = result.sort_values(
        ["days_as_subscriber", "user_id"], ascending=[False, True]
    ).reset_index(drop=True)
    return result
