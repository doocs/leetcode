import pandas as pd


def find_study_spiral_pattern(
    students: pd.DataFrame, study_sessions: pd.DataFrame
) -> pd.DataFrame:
    study_sessions = study_sessions.copy()
    study_sessions["session_date"] = pd.to_datetime(study_sessions["session_date"])

    result = []

    for student_id, group in study_sessions.groupby("student_id"):
        group = group.sort_values(["session_date", "session_id"]).reset_index(drop=True)
        # One gap longer than two days breaks the whole record. A later fragment
        # is not a separate spiral.
        if len(group) >= 2:
            gaps = group["session_date"].diff().dt.days.iloc[1:]
            if (gaps > 2).any():
                continue
        _check_pattern(student_id, [row for _, row in group.iterrows()], result)

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
    # Cycle length is the number of distinct subjects, not an arbitrary divisor.
    cycle_len = len(set(subjects))
    if cycle_len < 3 or n < cycle_len * 2 or n % cycle_len != 0:
        return

    first_cycle = subjects[:cycle_len]
    if subjects != first_cycle * (n // cycle_len):
        return

    result.append(
        {
            "student_id": student_id,
            "cycle_length": cycle_len,
            "total_study_hours": hours,
        }
    )
