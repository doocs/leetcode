import pandas as pd


def find_consistently_improving_employees(
    employees: pd.DataFrame, performance_reviews: pd.DataFrame
) -> pd.DataFrame:
    recent = (
        performance_reviews.sort_values(
            ["employee_id", "review_date"], ascending=[True, False]
        )
        .groupby("employee_id")
        .head(3)
    )

    three_reviews_ids = recent["employee_id"].value_counts().loc[lambda s: s == 3].index
    recent = recent[recent["employee_id"].isin(three_reviews_ids)]
    recent = recent.sort_values(["employee_id", "review_date"])

    def strictly_increasing(ratings: pd.Series) -> bool:
        return (ratings.diff().dropna() > 0).all()

    improving_ids = (
        recent.groupby("employee_id")["rating"]
        .apply(strictly_increasing)
        .loc[lambda s: s]
        .index
    )
    improving = recent[recent["employee_id"].isin(improving_ids)]

    scores = (
        improving.groupby("employee_id")["rating"]
        .agg(lambda x: x.iloc[-1] - x.iloc[0])
        .reset_index(name="improvement_score")
    )

    result = (
        scores.merge(employees, on="employee_id")
        .loc[:, ["employee_id", "name", "improvement_score"]]
        .sort_values(["improvement_score", "name"], ascending=[False, True])
        .reset_index(drop=True)
    )
    return result
