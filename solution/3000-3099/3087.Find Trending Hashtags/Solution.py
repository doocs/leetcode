import pandas as pd


def find_trending_hashtags(tweets: pd.DataFrame) -> pd.DataFrame:
    tweets = tweets[tweets["tweet_date"].dt.strftime("%Y%m") == "202402"]
    tweets["hashtag"] = "#" + tweets["tweet"].str.extract(r"#(\w+)")
    hashtag_counts = tweets["hashtag"].value_counts().reset_index()
    hashtag_counts.columns = ["hashtag", "hashtag_count"]
    hashtag_counts = hashtag_counts.sort_values(
        by=["hashtag_count", "hashtag"], ascending=[False, False]
    )
    top_3_hashtags = hashtag_counts.head(3)
    return top_3_hashtags
