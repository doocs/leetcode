import pandas as pd


def second_highest_salary(employee: pd.DataFrame) -> pd.DataFrame:
    # Drop any duplicate salary values to avoid counting duplicates as separate salary ranks
    unique_salaries = employee["salary"].drop_duplicates()

    # Sort the unique salaries in descending order and get the second highest salary
    second_highest = (
        unique_salaries.nlargest(2).iloc[-1] if len(unique_salaries) >= 2 else None
    )

    # If the second highest salary doesn't exist (e.g., there are fewer than two unique salaries), return None
    if second_highest is None:
        return pd.DataFrame({"SecondHighestSalary": [None]})

    # Create a DataFrame with the second highest salary
    result_df = pd.DataFrame({"SecondHighestSalary": [second_highest]})

    return result_df
