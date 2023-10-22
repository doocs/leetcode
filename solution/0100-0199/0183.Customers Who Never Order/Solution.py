import pandas as pd


def find_customers(customers: pd.DataFrame, orders: pd.DataFrame) -> pd.DataFrame:
    # Select the customers whose 'id' is not present in the orders DataFrame's 'customerId' column.
    df = customers[~customers["id"].isin(orders["customerId"])]

    # Build a DataFrame that only contains the 'name' column and rename it as 'Customers'.
    df = df[["name"]].rename(columns={"name": "Customers"})

    return df
