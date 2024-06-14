import pandas as pd


def count_apples_and_oranges(boxes: pd.DataFrame, chests: pd.DataFrame) -> pd.DataFrame:
    merged_df = boxes.merge(
        chests, on="chest_id", how="left", suffixes=("_box", "_chest")
    )
    apple_count = (
        merged_df["apple_count_box"].fillna(0)
        + merged_df["apple_count_chest"].fillna(0)
    ).sum()
    orange_count = (
        merged_df["orange_count_box"].fillna(0)
        + merged_df["orange_count_chest"].fillna(0)
    ).sum()
    return pd.DataFrame({"apple_count": [apple_count], "orange_count": [orange_count]})
