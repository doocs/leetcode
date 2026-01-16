import pandas as pd
from decimal import Decimal, ROUND_HALF_UP


def find_emotionally_consistent_users(reactions: pd.DataFrame) -> pd.DataFrame:
    t = reactions.groupby(["user_id", "reaction"]).size().reset_index(name="cnt")

    s = (
        t.groupby("user_id")
        .agg(mx_cnt=("cnt", "max"), total_cnt=("cnt", "sum"))
        .reset_index()
    )

    s["reaction_ratio"] = (
        s["mx_cnt"]
        .div(s["total_cnt"])
        .apply(
            lambda x: float(
                Decimal(str(x)).quantize(Decimal("0.00"), rounding=ROUND_HALF_UP)
            )
        )
    )

    s = s[(s["reaction_ratio"] >= 0.60) & (s["total_cnt"] >= 5)]

    merged = pd.merge(
        s[["user_id", "mx_cnt", "reaction_ratio"]],
        t,
        left_on=["user_id", "mx_cnt"],
        right_on=["user_id", "cnt"],
    )

    result = (
        merged[["user_id", "reaction", "reaction_ratio"]]
        .rename(columns={"reaction": "dominant_reaction"})
        .sort_values(by=["reaction_ratio", "user_id"], ascending=[False, True])
        .reset_index(drop=True)
    )

    return result
