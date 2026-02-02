import pandas as pd


def find_users_with_high_tokens(prompts: pd.DataFrame) -> pd.DataFrame:
    df = prompts.groupby("user_id", as_index=False).agg(
        prompt_count=("user_id", "size"),
        avg_tokens=("tokens", "mean"),
        max_tokens=("tokens", "max"),
    )

    df["avg_tokens"] = df["avg_tokens"].round(2)

    df = df[(df["prompt_count"] >= 3) & (df["max_tokens"] > df["avg_tokens"])]

    df = (
        df.sort_values(["avg_tokens", "user_id"], ascending=[False, True])
        .loc[:, ["user_id", "prompt_count", "avg_tokens"]]
        .reset_index(drop=True)
    )

    return df
