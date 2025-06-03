import pandas as pd


def seasonal_sales_analysis(
    products: pd.DataFrame, sales: pd.DataFrame
) -> pd.DataFrame:
    df = sales.merge(products, on="product_id")
    month_to_season = {
        12: "Winter",
        1: "Winter",
        2: "Winter",
        3: "Spring",
        4: "Spring",
        5: "Spring",
        6: "Summer",
        7: "Summer",
        8: "Summer",
        9: "Fall",
        10: "Fall",
        11: "Fall",
    }
    df["season"] = df["sale_date"].dt.month.map(month_to_season)
    seasonal_sales = df.groupby(["season", "category"], as_index=False).agg(
        total_quantity=("quantity", "sum"),
        total_revenue=("quantity", lambda x: (x * df.loc[x.index, "price"]).sum()),
    )
    seasonal_sales["rk"] = (
        seasonal_sales.sort_values(
            ["season", "total_quantity", "total_revenue"],
            ascending=[True, False, False],
        )
        .groupby("season")
        .cumcount()
        + 1
    )
    result = seasonal_sales[seasonal_sales["rk"] == 1].copy()
    return result[
        ["season", "category", "total_quantity", "total_revenue"]
    ].sort_values("season")
