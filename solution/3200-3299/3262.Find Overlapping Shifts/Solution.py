import pandas as pd


def find_overlapping_shifts(employee_shifts: pd.DataFrame) -> pd.DataFrame:
    merged_shifts = employee_shifts.merge(
        employee_shifts, on="employee_id", suffixes=("_t1", "_t2")
    )
    overlapping_shifts = merged_shifts[
        (merged_shifts["start_time_t1"] < merged_shifts["start_time_t2"])
        & (merged_shifts["end_time_t1"] > merged_shifts["start_time_t2"])
    ]
    result = (
        overlapping_shifts.groupby("employee_id")
        .size()
        .reset_index(name="overlapping_shifts")
    )
    result = result[result["overlapping_shifts"] > 0]
    result = result.sort_values("employee_id").reset_index(drop=True)
    return result
