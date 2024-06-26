import pandas as pd


def find_cities(cities: pd.DataFrame) -> pd.DataFrame:
    result = (
        cities.groupby("state")["city"]
        .apply(lambda x: ", ".join(sorted(x)))
        .reset_index()
    )
    result.columns = ["state", "cities"]
    return result
