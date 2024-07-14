import pandas as pd


def find_invalid_tweets(tweets: pd.DataFrame) -> pd.DataFrame:
    invalid_tweets = tweets[
        (tweets["content"].str.len() > 140)
        | (tweets["content"].str.count("@") > 3)
        | (tweets["content"].str.count("#") > 3)
    ].sort_values(by="tweet_id")
    return invalid_tweets[["tweet_id"]]
