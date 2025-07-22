import pandas as pd


def calculate_final_prices(
    products: pd.DataFrame, discounts: pd.DataFrame
) -> pd.DataFrame:
    # Perform a left join on the 'category' column
    merged_df = pd.merge(products, discounts, on="category", how="left")

    # Calculate the final price
    merged_df["final_price"] = (
        merged_df["price"] * (100 - merged_df["discount"].fillna(0)) / 100
    )

    # Select the necessary columns and sort by 'product_id'
    result_df = merged_df[["product_id", "final_price", "category"]].sort_values(
        "product_id"
    )

    return result_df
