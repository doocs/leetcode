import pandas as pd


def find_overlapping_shifts(employee_shifts: pd.DataFrame) -> pd.DataFrame:
    merged = employee_shifts.merge(
        employee_shifts, on="employee_id", suffixes=("_1", "_2")
    )
    overlap = merged[
        (merged["start_time_1"] < merged["end_time_2"])
        & (merged["end_time_1"] > merged["start_time_2"])
        & (merged["start_time_1"] != merged["start_time_2"])
    ]
    overlap_counts = (
        overlap.groupby("employee_id").size().reset_index(name="overlapping_shifts")
    )
    overlap_counts["overlapping_shifts"] = overlap_counts["overlapping_shifts"] // 2
    result = (
        overlap_counts[overlap_counts["overlapping_shifts"] > 0]
        .sort_values("employee_id")
        .reset_index(drop=True)
    )
    return result
