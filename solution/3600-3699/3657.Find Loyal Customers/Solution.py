import pandas as pd


def find_loyal_customers(customer_transactions: pd.DataFrame) -> pd.DataFrame:
    customer_transactions["transaction_date"] = pd.to_datetime(
        customer_transactions["transaction_date"]
    )
    grouped = customer_transactions.groupby("customer_id")
    agg_df = grouped.agg(
        total_transactions=("transaction_type", "size"),
        refund_count=("transaction_type", lambda x: (x == "refund").sum()),
        min_date=("transaction_date", "min"),
        max_date=("transaction_date", "max"),
    ).reset_index()
    agg_df["date_diff"] = (agg_df["max_date"] - agg_df["min_date"]).dt.days
    agg_df["refund_ratio"] = agg_df["refund_count"] / agg_df["total_transactions"]
    result = (
        agg_df[
            (agg_df["total_transactions"] >= 3)
            & (agg_df["refund_ratio"] < 0.2)
            & (agg_df["date_diff"] >= 30)
        ][["customer_id"]]
        .sort_values("customer_id")
        .reset_index(drop=True)
    )
    return result
