import pandas as pd


def find_zombie_sessions(app_events: pd.DataFrame) -> pd.DataFrame:
    if not pd.api.types.is_datetime64_any_dtype(app_events["event_timestamp"]):
        app_events["event_timestamp"] = pd.to_datetime(app_events["event_timestamp"])

    grouped = app_events.groupby(["session_id", "user_id"])

    result = grouped.agg(
        session_duration_minutes=(
            "event_timestamp",
            lambda x: (x.max() - x.min()).total_seconds() // 60,
        ),
        scroll_count=("event_type", lambda x: (x == "scroll").sum()),
        click_count=("event_type", lambda x: (x == "click").sum()),
        purchase_count=("event_type", lambda x: (x == "purchase").sum()),
    ).reset_index()

    result = result[
        (result["session_duration_minutes"] >= 30)
        & (result["click_count"] / result["scroll_count"] < 0.2)
        & (result["purchase_count"] == 0)
        & (result["scroll_count"] >= 5)
    ]

    result = result.sort_values(
        by=["scroll_count", "session_id"], ascending=[False, True]
    ).reset_index(drop=True)

    return result[["session_id", "user_id", "session_duration_minutes", "scroll_count"]]
