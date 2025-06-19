import pandas as pd


def find_covid_recovery_patients(
    patients: pd.DataFrame, covid_tests: pd.DataFrame
) -> pd.DataFrame:
    covid_tests["test_date"] = pd.to_datetime(covid_tests["test_date"])

    pos = (
        covid_tests[covid_tests["result"] == "Positive"]
        .groupby("patient_id", as_index=False)["test_date"]
        .min()
    )
    pos.rename(columns={"test_date": "first_positive_date"}, inplace=True)

    neg = covid_tests.merge(pos, on="patient_id")
    neg = neg[
        (neg["result"] == "Negative") & (neg["test_date"] > neg["first_positive_date"])
    ]
    neg = neg.groupby("patient_id", as_index=False)["test_date"].min()
    neg.rename(columns={"test_date": "first_negative_date"}, inplace=True)

    df = pos.merge(neg, on="patient_id")
    df["recovery_time"] = (
        df["first_negative_date"] - df["first_positive_date"]
    ).dt.days

    out = df.merge(patients, on="patient_id")[
        ["patient_id", "patient_name", "age", "recovery_time"]
    ]
    return out.sort_values(by=["recovery_time", "patient_name"]).reset_index(drop=True)
