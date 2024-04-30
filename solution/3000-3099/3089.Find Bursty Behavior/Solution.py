import pandas as pd


def find_bursty_behavior(posts: pd.DataFrame) -> pd.DataFrame:
    # Subquery P
    p1 = pd.merge(posts, posts, on="user_id", suffixes=("_1", "_2"))
    p1 = p1[
        p1["post_date_2"].between(
            p1["post_date_1"], p1["post_date_1"] + pd.Timedelta(days=6)
        )
    ]
    p1 = p1.groupby(["user_id", "post_id_1"]).size().reset_index(name="cnt")

    # Subquery T
    t = posts[
        (posts["post_date"] >= "2024-02-01") & (posts["post_date"] <= "2024-02-28")
    ]
    t = t.groupby("user_id").size().div(4).reset_index(name="avg_weekly_posts")

    # Joining P and T
    merged_df = pd.merge(p1, t, on="user_id", how="inner")

    # Filtering
    filtered_df = merged_df[merged_df["cnt"] >= merged_df["avg_weekly_posts"] * 2]

    # Aggregating
    result_df = (
        filtered_df.groupby("user_id")
        .agg({"cnt": "max", "avg_weekly_posts": "first"})
        .reset_index()
    )
    result_df.columns = ["user_id", "max_7day_posts", "avg_weekly_posts"]

    # Sorting
    result_df.sort_values(by="user_id", inplace=True)

    return result_df
