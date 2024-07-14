import pandas as pd


def find_trending_hashtags(tweets: pd.DataFrame) -> pd.DataFrame:
    # Filter tweets for February 2024
    tweets_feb_2024 = tweets[tweets["tweet_date"].between("2024-02-01", "2024-02-29")]

    # Extract hashtags from tweets
    hashtags = tweets_feb_2024["tweet"].str.findall(r"#\w+")

    # Flatten list of hashtags
    all_hashtags = [tag for sublist in hashtags for tag in sublist]

    # Count occurrences of each hashtag
    hashtag_counts = pd.Series(all_hashtags).value_counts().reset_index()
    hashtag_counts.columns = ["hashtag", "count"]

    # Sort by count of hashtag in descending order
    hashtag_counts = hashtag_counts.sort_values(
        by=["count", "hashtag"], ascending=[False, False]
    )

    # Get top 3 trending hashtags
    top_3_hashtags = hashtag_counts.head(3)

    return top_3_hashtags
