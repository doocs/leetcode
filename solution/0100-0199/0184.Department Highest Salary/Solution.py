import pandas as pd


def department_highest_salary(
    employee: pd.DataFrame, department: pd.DataFrame
) -> pd.DataFrame:
    # Find the top 1 highest salaries in each department using nlargest,
    # keeping employees with the same salary using keep='all'
    # Use apply and lambda to maintain the DataFrame's shape
    df_top1 = (
        employee.groupby("departmentId")
        .apply(lambda x: x.nlargest(1, "salary", keep="all"))
        .reset_index(drop=True)
    )

    # Merge the result with the department DataFrame to get department names
    df_joined = pd.merge(
        df_top1,
        department,
        left_on="departmentId",
        right_on="id",
        how="inner",
        suffixes=("_employee", "_department"),
    )

    # Rename columns for clarity
    df_joined.rename(
        columns={
            "name_employee": "Employee",
            "name_department": "Department",
            "salary": "Salary",
        },
        inplace=True,
    )

    # Return a DataFrame with only the desired columns
    return df_joined[["Department", "Employee", "Salary"]]
