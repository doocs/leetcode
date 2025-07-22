import pandas as pd


def find_consistently_improving_employees(
    employees: pd.DataFrame, performance_reviews: pd.DataFrame
) -> pd.DataFrame:
    performance_reviews = performance_reviews.sort_values(
        ["employee_id", "review_date"], ascending=[True, False]
    )
    performance_reviews["rn"] = (
        performance_reviews.groupby("employee_id").cumcount() + 1
    )
    performance_reviews["lag_rating"] = performance_reviews.groupby("employee_id")[
        "rating"
    ].shift(1)
    performance_reviews["delta"] = (
        performance_reviews["lag_rating"] - performance_reviews["rating"]
    )
    recent = performance_reviews[
        (performance_reviews["rn"] > 1) & (performance_reviews["rn"] <= 3)
    ]
    improvement = (
        recent.groupby("employee_id")
        .agg(
            improvement_score=("delta", "sum"),
            count=("delta", "count"),
            min_delta=("delta", "min"),
        )
        .reset_index()
    )
    improvement = improvement[
        (improvement["count"] == 2) & (improvement["min_delta"] > 0)
    ]
    result = improvement.merge(employees[["employee_id", "name"]], on="employee_id")
    result = result.sort_values(
        by=["improvement_score", "name"], ascending=[False, True]
    )
    return result[["employee_id", "name", "improvement_score"]]
