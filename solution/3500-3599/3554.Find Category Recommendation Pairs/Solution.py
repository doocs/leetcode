import pandas as pd


def find_category_recommendation_pairs(
    product_purchases: pd.DataFrame, product_info: pd.DataFrame
) -> pd.DataFrame:
    df = product_purchases[["user_id", "product_id"]].merge(
        product_info[["product_id", "category"]], on="product_id", how="inner"
    )
    user_category = df.drop_duplicates(subset=["user_id", "category"])
    pair_per_user = (
        user_category.merge(user_category, on="user_id")
        .query("category_x < category_y")
        .rename(columns={"category_x": "category1", "category_y": "category2"})
    )
    pair_counts = (
        pair_per_user.groupby(["category1", "category2"])["user_id"]
        .nunique()
        .reset_index(name="customer_count")
    )
    result = (
        pair_counts.query("customer_count >= 3")
        .sort_values(
            ["customer_count", "category1", "category2"], ascending=[False, True, True]
        )
        .reset_index(drop=True)
    )
    return result
