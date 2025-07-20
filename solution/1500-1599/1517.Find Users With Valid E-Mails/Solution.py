import pandas as pd


def valid_emails(users: pd.DataFrame) -> pd.DataFrame:
    pattern = r"^[A-Za-z][A-Za-z0-9_.-]*@leetcode\.com$"
    mask = users["mail"].str.match(pattern, flags=0, na=False)
    return users.loc[mask, ["user_id", "name", "mail"]]
