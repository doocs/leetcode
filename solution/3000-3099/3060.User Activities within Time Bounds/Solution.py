import pandas as pd


def user_activities(sessions: pd.DataFrame) -> pd.DataFrame:
    sessions = sessions.sort_values(by=["user_id", "session_start"])
    sessions["prev_session_end"] = sessions.groupby(["user_id", "session_type"])[
        "session_end"
    ].shift(1)
    sessions_filtered = sessions[
        sessions["session_start"] - sessions["prev_session_end"]
        <= pd.Timedelta(hours=12)
    ]
    return pd.DataFrame({"user_id": sessions_filtered["user_id"].unique()})
