import pandas as pd


def find_improved_efficiency_drivers(
    drivers: pd.DataFrame, trips: pd.DataFrame
) -> pd.DataFrame:
    trips = trips.copy()
    trips["trip_date"] = pd.to_datetime(trips["trip_date"])
    trips["half"] = trips["trip_date"].dt.month.apply(lambda m: 1 if m <= 6 else 2)
    trips["efficiency"] = trips["distance_km"] / trips["fuel_consumed"]
    half_avg = (
        trips.groupby(["driver_id", "half"])["efficiency"]
        .mean()
        .reset_index(name="half_avg")
    )
    pivot = half_avg.pivot(index="driver_id", columns="half", values="half_avg").rename(
        columns={1: "first_half_avg", 2: "second_half_avg"}
    )
    pivot = pivot.dropna()
    pivot = pivot[pivot["second_half_avg"] > pivot["first_half_avg"]]
    pivot["efficiency_improvement"] = (
        pivot["second_half_avg"] - pivot["first_half_avg"]
    ).round(2)
    pivot["first_half_avg"] = pivot["first_half_avg"].round(2)
    pivot["second_half_avg"] = pivot["second_half_avg"].round(2)
    result = pivot.reset_index().merge(drivers, on="driver_id")
    result = result.sort_values(
        by=["efficiency_improvement", "driver_name"], ascending=[False, True]
    )
    return result[
        [
            "driver_id",
            "driver_name",
            "first_half_avg",
            "second_half_avg",
            "efficiency_improvement",
        ]
    ]
