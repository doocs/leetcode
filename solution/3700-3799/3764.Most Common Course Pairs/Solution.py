import pandas as pd


def topLearnerCourseTransitions(course_completions: pd.DataFrame) -> pd.DataFrame:
    grp = course_completions.groupby("user_id")
    top_students = grp.filter(
        lambda df: df.shape[0] >= 5 and df["course_rating"].mean() >= 4
    )["user_id"].unique()

    df = course_completions[course_completions["user_id"].isin(top_students)].copy()
    df = df.sort_values(["user_id", "completion_date"])
    df["second_course"] = df.groupby("user_id")["course_name"].shift(-1)
    df["first_course"] = df["course_name"]

    pairs = df[df["second_course"].notna()][["first_course", "second_course"]]

    result = (
        pairs.groupby(["first_course", "second_course"])
        .size()
        .reset_index(name="transition_count")
        .sort_values(
            ["transition_count", "first_course", "second_course"],
            ascending=[False, True, True],
            key=lambda col: col.str.lower() if col.dtype == "object" else col,
        )
        .reset_index(drop=True)
    )

    return result
