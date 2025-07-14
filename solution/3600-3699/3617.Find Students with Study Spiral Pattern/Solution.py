import pandas as pd
from datetime import timedelta


def find_study_spiral_pattern(
    students: pd.DataFrame, study_sessions: pd.DataFrame
) -> pd.DataFrame:
    # Convert session_date to datetime
    study_sessions["session_date"] = pd.to_datetime(study_sessions["session_date"])

    result = []

    # Group study sessions by student
    for student_id, group in study_sessions.groupby("student_id"):
        # Sort sessions by date
        group = group.sort_values("session_date").reset_index(drop=True)

        temp = []  # Holds current contiguous segment
        last_date = None

        for idx, row in group.iterrows():
            if not temp:
                temp.append(row)
            else:
                delta = (row["session_date"] - last_date).days
                if delta <= 2:
                    temp.append(row)
                else:
                    # Check the previous contiguous segment
                    if len(temp) >= 6:
                        _check_pattern(student_id, temp, result)
                    temp = [row]
            last_date = row["session_date"]

        # Check the final segment
        if len(temp) >= 6:
            _check_pattern(student_id, temp, result)

    # Build result DataFrame
    df_result = pd.DataFrame(
        result, columns=["student_id", "cycle_length", "total_study_hours"]
    )

    if df_result.empty:
        return pd.DataFrame(
            columns=[
                "student_id",
                "student_name",
                "major",
                "cycle_length",
                "total_study_hours",
            ]
        )

    # Join with students table to get name and major
    df_result = df_result.merge(students, on="student_id")

    df_result = df_result[
        ["student_id", "student_name", "major", "cycle_length", "total_study_hours"]
    ]

    return df_result.sort_values(
        by=["cycle_length", "total_study_hours"], ascending=[False, False]
    ).reset_index(drop=True)


def _check_pattern(student_id, sessions, result):
    subjects = [row["subject"] for row in sessions]
    hours = sum(row["hours_studied"] for row in sessions)

    n = len(subjects)

    # Try possible cycle lengths from 3 up to half of the sequence
    for cycle_len in range(3, n // 2 + 1):
        if n % cycle_len != 0:
            continue

        # Extract the first cycle
        first_cycle = subjects[:cycle_len]
        is_pattern = True

        # Compare each following cycle with the first
        for i in range(1, n // cycle_len):
            if subjects[i * cycle_len : (i + 1) * cycle_len] != first_cycle:
                is_pattern = False
                break

        # If a repeated cycle is detected, store the result
        if is_pattern:
            result.append(
                {
                    "student_id": student_id,
                    "cycle_length": cycle_len,
                    "total_study_hours": hours,
                }
            )
            break  # Stop at the first valid cycle found
