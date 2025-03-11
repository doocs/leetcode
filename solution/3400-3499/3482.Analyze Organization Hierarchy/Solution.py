import pandas as pd


def analyze_organization_hierarchy(employees: pd.DataFrame) -> pd.DataFrame:
    # Copy the input DataFrame to avoid modifying the original
    employees = employees.copy()
    employees["level"] = None

    # Identify the CEO (level 1)
    ceo_id = employees.loc[employees["manager_id"].isna(), "employee_id"].values[0]
    employees.loc[employees["employee_id"] == ceo_id, "level"] = 1

    # Recursively compute employee levels
    def compute_levels(emp_df, level):
        next_level_ids = emp_df[emp_df["level"] == level]["employee_id"].tolist()
        if not next_level_ids:
            return
        emp_df.loc[emp_df["manager_id"].isin(next_level_ids), "level"] = level + 1
        compute_levels(emp_df, level + 1)

    compute_levels(employees, 1)

    # Initialize team size and budget dictionaries
    team_size = {eid: 0 for eid in employees["employee_id"]}
    budget = {
        eid: salary
        for eid, salary in zip(employees["employee_id"], employees["salary"])
    }

    # Compute team size and budget for each employee
    for eid in sorted(employees["employee_id"], reverse=True):
        manager_id = employees.loc[
            employees["employee_id"] == eid, "manager_id"
        ].values[0]
        if pd.notna(manager_id):
            team_size[manager_id] += team_size[eid] + 1
            budget[manager_id] += budget[eid]

    # Map computed team size and budget to employees DataFrame
    employees["team_size"] = employees["employee_id"].map(team_size)
    employees["budget"] = employees["employee_id"].map(budget)

    # Sort the final result by level (ascending), budget (descending), and employee name (ascending)
    employees = employees.sort_values(
        by=["level", "budget", "employee_name"], ascending=[True, False, True]
    )

    return employees[["employee_id", "employee_name", "level", "team_size", "budget"]]
