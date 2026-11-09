class Solution:
    def isAcronym(self, words: list[str], s: str) -> bool:
        return "".join(w[0] for w in words) == s
