import pandas as pd


def sum_daily_odd_even(transactions: pd.DataFrame) -> pd.DataFrame:
    transactions["odd_sum"] = transactions["amount"].where(
        transactions["amount"] % 2 == 1, 0
    )
    transactions["even_sum"] = transactions["amount"].where(
        transactions["amount"] % 2 == 0, 0
    )

    result = (
        transactions.groupby("transaction_date")
        .agg(odd_sum=("odd_sum", "sum"), even_sum=("even_sum", "sum"))
        .reset_index()
    )

    result = result.sort_values("transaction_date")

    return result
