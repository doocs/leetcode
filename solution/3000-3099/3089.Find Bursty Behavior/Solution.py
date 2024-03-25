import pandas as pd


def find_bursty_behavior(posts: pd.DataFrame) -> pd.DataFrame:
    # Calculate the count of posts made by each user within a 7-day window
    p = posts.merge(posts, on="user_id")
    p = p[
        (p["post_date_y"] >= p["post_date_x"])
        & (p["post_date_y"] <= p["post_date_x"] + pd.Timedelta(days=6))
    ]
    p_count = p.groupby(["user_id", "post_date_x"]).size().reset_index(name="cnt")

    # Calculate the average weekly posts for each user in February 2024
    t = posts[
        (posts["post_date"] >= "2024-02-01") & (posts["post_date"] <= "2024-02-28")
    ]
    t_count = t.groupby("user_id").size().reset_index(name="count")
    t_count["avg_weekly_posts"] = t_count["count"] / 4

    # Joining the two calculated tables and filtering users meeting the criteria
    merged_df = p_count.merge(t_count, on="user_id")
    merged_df = merged_df.groupby("user_id").agg(
        max_7day_posts=("cnt", "max"), avg_weekly_posts=("avg_weekly_posts", "first")
    )
    result_df = merged_df[
        merged_df["max_7day_posts"] >= merged_df["avg_weekly_posts"] * 2
    ].reset_index()

    return result_df.sort_values("user_id")
