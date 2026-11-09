class WordsFrequency:
    def __init__(self, book: list[str]):
        self.cnt = Counter(book)

    def get(self, word: str) -> int:
        return self.cnt[word]


# Your WordsFrequency object will be instantiated and called as such:
# obj = WordsFrequency(book)
# param_1 = obj.get(word)
