class Solution:
    def isAcronym(self, words: list[str], s: str) -> bool:
        return len(words) == len(s) and all(w[0] == c for w, c in zip(words, s))
