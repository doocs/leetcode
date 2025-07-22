import pandas as pd


def find_valid_serial_products(products: pd.DataFrame) -> pd.DataFrame:
    valid_pattern = r"\bSN[0-9]{4}-[0-9]{4}\b"
    valid_products = products[
        products["description"].str.contains(valid_pattern, regex=True)
    ]
    valid_products = valid_products.sort_values(by="product_id")
    return valid_products
