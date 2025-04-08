import pandas as pd


def department_highest_salary(
    employee: pd.DataFrame, department: pd.DataFrame
) -> pd.DataFrame:
    # Merge the two tables on departmentId and department id
    merged = employee.merge(department, left_on='departmentId', right_on='id')

    # Find the maximum salary for each department
    max_salaries = merged.groupby('departmentId')['salary'].transform('max')

    # Filter employees who have the highest salary in their department
    top_earners = merged[merged['salary'] == max_salaries]

    # Select required columns and rename them
    result = top_earners[['name_y', 'name_x', 'salary']].copy()
    result.columns = ['Department', 'Employee', 'Salary']

    return result
