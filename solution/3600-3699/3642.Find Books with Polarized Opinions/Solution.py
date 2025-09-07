import pandas as pd
from decimal import Decimal, ROUND_HALF_UP


def find_polarized_books(
    books: pd.DataFrame, reading_sessions: pd.DataFrame
) -> pd.DataFrame:
    df = books.merge(reading_sessions, on="book_id")
    agg_df = (
        df.groupby(["book_id", "title", "author", "genre", "pages"])
        .agg(
            max_rating=("session_rating", "max"),
            min_rating=("session_rating", "min"),
            rating_spread=("session_rating", lambda x: x.max() - x.min()),
            count_sessions=("session_rating", "count"),
            low_or_high_count=("session_rating", lambda x: ((x <= 2) | (x >= 4)).sum()),
        )
        .reset_index()
    )

    agg_df["polarization_score"] = agg_df.apply(
        lambda r: float(
            Decimal(r["low_or_high_count"] / r["count_sessions"]).quantize(
                Decimal("0.01"), rounding=ROUND_HALF_UP
            )
        ),
        axis=1,
    )

    result = agg_df[
        (agg_df["count_sessions"] >= 5)
        & (agg_df["max_rating"] >= 4)
        & (agg_df["min_rating"] <= 2)
        & (agg_df["polarization_score"] >= 0.6)
    ]

    return result.sort_values(
        by=["polarization_score", "title"], ascending=[False, False]
    )[
        [
            "book_id",
            "title",
            "author",
            "genre",
            "pages",
            "rating_spread",
            "polarization_score",
        ]
    ]
