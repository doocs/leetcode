import pandas as pd


def find_books_with_no_available_copies(
    library_books: pd.DataFrame, borrowing_records: pd.DataFrame
) -> pd.DataFrame:
    current_borrowers = (
        borrowing_records[borrowing_records["return_date"].isna()]
        .groupby("book_id")
        .size()
        .rename("current_borrowers")
        .reset_index()
    )

    merged = library_books.merge(current_borrowers, on="book_id", how="inner")
    fully_borrowed = merged[merged["current_borrowers"] == merged["total_copies"]]
    fully_borrowed = fully_borrowed.sort_values(
        by=["current_borrowers", "title"], ascending=[False, True]
    )

    cols = [
        "book_id",
        "title",
        "author",
        "genre",
        "publication_year",
        "current_borrowers",
    ]
    return fully_borrowed[cols].reset_index(drop=True)
