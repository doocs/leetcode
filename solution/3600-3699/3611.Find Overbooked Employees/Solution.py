import pandas as pd


def find_overbooked_employees(
    employees: pd.DataFrame, meetings: pd.DataFrame
) -> pd.DataFrame:
    meetings["meeting_date"] = pd.to_datetime(meetings["meeting_date"])
    meetings["year"] = meetings["meeting_date"].dt.isocalendar().year
    meetings["week"] = meetings["meeting_date"].dt.isocalendar().week

    week_meeting_hours = (
        meetings.groupby(["employee_id", "year", "week"], as_index=False)[
            "duration_hours"
        ]
        .sum()
        .rename(columns={"duration_hours": "hours"})
    )

    intensive_weeks = week_meeting_hours[week_meeting_hours["hours"] >= 20]

    intensive_count = (
        intensive_weeks.groupby("employee_id")
        .size()
        .reset_index(name="meeting_heavy_weeks")
    )

    result = intensive_count.merge(employees, on="employee_id")

    result = result[result["meeting_heavy_weeks"] >= 2]

    result = result.sort_values(
        ["meeting_heavy_weeks", "employee_name"], ascending=[False, True]
    )

    return result[
        ["employee_id", "employee_name", "department", "meeting_heavy_weeks"]
    ].reset_index(drop=True)
