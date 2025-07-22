import pandas as pd


def find_valid_emails(users: pd.DataFrame) -> pd.DataFrame:
    email_pattern = r"^[A-Za-z0-9_]+@[A-Za-z][A-Za-z0-9]*\.com$"
    valid_emails = users[users["email"].str.match(email_pattern)]
    valid_emails = valid_emails.sort_values(by="user_id")
    return valid_emails
