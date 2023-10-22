import pandas as pd


def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    all_the_same = lambda lst: lst.nunique() == 1
    logs["is_consecutive"] = (
        logs["num"].rolling(window=3, center=True, min_periods=3).apply(all_the_same)
    )
    return (
        logs.query("is_consecutive == 1.0")[["num"]]
        .drop_duplicates()
        .rename(columns={"num": "ConsecutiveNums"})
    )
