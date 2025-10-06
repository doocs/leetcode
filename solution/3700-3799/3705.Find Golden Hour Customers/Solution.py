import pandas as pd
import numpy as np


def find_golden_hour_customers(restaurant_orders: pd.DataFrame) -> pd.DataFrame:
    df = restaurant_orders.copy()
    df["order_timestamp"] = pd.to_datetime(df["order_timestamp"])
    df["is_peak_hour"] = df["order_timestamp"].dt.time.between(
        pd.to_datetime("11:00:00").time(), pd.to_datetime("14:00:00").time()
    ) | df["order_timestamp"].dt.time.between(
        pd.to_datetime("18:00:00").time(), pd.to_datetime("21:00:00").time()
    )
    grouped = (
        df.groupby("customer_id")
        .agg(
            total_orders=("order_timestamp", "count"),
            peak_hour_count=("is_peak_hour", "sum"),
            average_rating=("order_rating", lambda x: x.dropna().mean()),
            non_null_rating_count=("order_rating", lambda x: x.notna().sum()),
        )
        .reset_index()
    )
    grouped["average_rating"] = grouped["average_rating"].round(2)
    grouped["peak_hour_percentage"] = (
        grouped["peak_hour_count"] / grouped["total_orders"] * 100
    ).round()
    filtered = grouped[
        (grouped["total_orders"] >= 3)
        & (grouped["peak_hour_percentage"] >= 60)
        & (grouped["average_rating"] >= 4.0)
        & (grouped["non_null_rating_count"] / grouped["total_orders"] >= 0.5)
    ]
    filtered = filtered.sort_values(
        by=["average_rating", "customer_id"], ascending=[False, False]
    )
    return filtered[
        ["customer_id", "total_orders", "peak_hour_percentage", "average_rating"]
    ]
