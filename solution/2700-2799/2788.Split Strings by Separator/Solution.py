class Solution:
    def splitWordsBySeparator(self, words: list[str], separator: str) -> list[str]:
        return [s for w in words for s in w.split(separator) if s]
