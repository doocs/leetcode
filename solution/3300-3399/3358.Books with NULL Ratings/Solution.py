import pandas as pd


def find_unrated_books(books: pd.DataFrame) -> pd.DataFrame:
    unrated_books = books[books["rating"].isnull()]
    return unrated_books[["book_id", "title", "author", "published_year"]].sort_values(
        by="book_id"
    )
