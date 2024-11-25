import pandas as pd


def process_text(user_content: pd.DataFrame) -> pd.DataFrame:
    user_content["converted_text"] = user_content["content_text"].apply(
        lambda text: " ".join(word.capitalize() for word in text.split(" "))
    )
    return user_content[["content_id", "content_text", "converted_text"]].rename(
        columns={"content_text": "original_text"}
    )
