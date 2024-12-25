import pandas as pd


def unique_orders_and_customers(orders: pd.DataFrame) -> pd.DataFrame:
    filtered_orders = orders[orders["invoice"] > 20]
    filtered_orders["month"] = (
        filtered_orders["order_date"].dt.to_period("M").astype(str)
    )
    result = (
        filtered_orders.groupby("month")
        .agg(
            order_count=("order_id", "count"), customer_count=("customer_id", "nunique")
        )
        .reset_index()
    )
    return result
