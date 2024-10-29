import pandas as pd


def find_second_highest_salary(employees: pd.DataFrame) -> pd.DataFrame:
    employees["rk"] = employees.groupby("dept")["salary"].rank(
        method="dense", ascending=False
    )
    second_highest = employees[employees["rk"] == 2][["emp_id", "dept"]]
    return second_highest.sort_values(by="emp_id")
