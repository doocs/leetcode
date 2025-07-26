import pandas as pd


def find_inventory_imbalance(
    stores: pd.DataFrame, inventory: pd.DataFrame
) -> pd.DataFrame:
    # Step 1: Identify stores with at least 3 products
    store_counts = inventory["store_id"].value_counts()
    valid_stores = store_counts[store_counts >= 3].index

    # Step 2: Find most expensive product for each valid store
    # Sort by price (descending) then quantity (descending) and take first record per store
    most_expensive = (
        inventory[inventory["store_id"].isin(valid_stores)]
        .sort_values(["store_id", "price", "quantity"], ascending=[True, False, False])
        .groupby("store_id")
        .first()
        .reset_index()
    )

    # Step 3: Find cheapest product for each store
    # Sort by price (ascending) then quantity (descending) and take first record per store
    cheapest = (
        inventory.sort_values(
            ["store_id", "price", "quantity"], ascending=[True, True, False]
        )
        .groupby("store_id")
        .first()
        .reset_index()
    )

    # Step 4: Merge the two datasets on store_id
    merged = pd.merge(
        most_expensive, cheapest, on="store_id", suffixes=("_most", "_cheap")
    )

    # Step 5: Filter for cases where cheapest product has higher quantity than most expensive
    result = merged[merged["quantity_most"] < merged["quantity_cheap"]].copy()

    # Step 6: Calculate imbalance ratio (cheapest quantity / most expensive quantity)
    result["imbalance_ratio"] = (
        result["quantity_cheap"] / result["quantity_most"]
    ).round(2)

    # Step 7: Merge with store information to get store names and locations
    result = pd.merge(result, stores, on="store_id")

    # Step 8: Select and rename columns for final output
    result = result[
        [
            "store_id",
            "store_name",
            "location",
            "product_name_most",
            "product_name_cheap",
            "imbalance_ratio",
        ]
    ].rename(
        columns={
            "product_name_most": "most_exp_product",
            "product_name_cheap": "cheapest_product",
        }
    )

    # Step 9: Sort by imbalance ratio (descending) then store name (ascending)
    result = result.sort_values(
        ["imbalance_ratio", "store_name"], ascending=[False, True]
    ).reset_index(drop=True)

    return result
