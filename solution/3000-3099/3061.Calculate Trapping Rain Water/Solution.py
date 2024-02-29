import pandas as pd


def calculate_trapped_rain_water(heights: pd.DataFrame) -> pd.DataFrame:
    heights["l"] = heights["height"].cummax()
    heights["r"] = heights["height"][::-1].cummax()[::-1]
    heights["trapped_water"] = heights[["l", "r"]].min(axis=1) - heights["height"]
    return pd.DataFrame({"total_trapped_water": [heights["trapped_water"].sum()]})
