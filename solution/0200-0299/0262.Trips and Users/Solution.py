import pandas as pd


def trips_and_users(trips: pd.DataFrame, users: pd.DataFrame) -> pd.DataFrame:
    # 1) temporal filtering
    trips = trips[trips["request_at"].between("2013-10-01", "2013-10-03")].rename(
        columns={"request_at": "Day"}
    )

    # 2) filtering based not banned
    # 2.1) mappning the column 'banned' to `client_id` and `driver_id`
    df_client = (
        pd.merge(trips, users, left_on="client_id", right_on="users_id", how="left")
        .drop(["users_id", "role"], axis=1)
        .rename(columns={"banned": "banned_client"})
    )
    df_driver = (
        pd.merge(trips, users, left_on="driver_id", right_on="users_id", how="left")
        .drop(["users_id", "role"], axis=1)
        .rename(columns={"banned": "banned_driver"})
    )
    df = pd.merge(
        df_client,
        df_driver,
        left_on=["id", "driver_id", "client_id", "city_id", "status", "Day"],
        right_on=["id", "driver_id", "client_id", "city_id", "status", "Day"],
        how="left",
    )
    # 2.2) filtering based on not banned
    df = df[(df["banned_client"] == "No") & (df["banned_driver"] == "No")]

    # 3) counting the cancelled and total trips per day
    df["status_cancelled"] = df["status"].str.contains("cancelled")
    df = df[["Day", "status_cancelled"]]
    df = df.groupby("Day").agg(
        {"status_cancelled": [("total_cancelled", "sum"), ("total", "count")]}
    )
    df.columns = df.columns.droplevel()
    df = df.reset_index()

    # 4) calculating the ratio
    df["Cancellation Rate"] = (df["total_cancelled"] / df["total"]).round(2)
    return df[["Day", "Cancellation Rate"]]
