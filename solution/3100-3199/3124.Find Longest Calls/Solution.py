import pandas as pd


def find_longest_calls(contacts: pd.DataFrame, calls: pd.DataFrame) -> pd.DataFrame:
    merged_data = calls.merge(contacts, left_on="contact_id", right_on="id")
    merged_data["duration_formatted"] = (
        merged_data["duration"] // 3600 * 10000
        + merged_data["duration"] % 3600 // 60 * 100
        + merged_data["duration"] % 60
    ).apply(lambda x: "{:02}:{:02}:{:02}".format(x // 10000, x // 100 % 100, x % 100))

    merged_data["rk"] = merged_data.groupby("type")["duration"].rank(
        method="dense", ascending=False
    )

    result = merged_data[merged_data["rk"] <= 3][
        ["first_name", "type", "duration_formatted"]
    ]
    result = result.sort_values(
        by=["type", "duration_formatted", "first_name"], ascending=[True, False, False]
    )
    return result
