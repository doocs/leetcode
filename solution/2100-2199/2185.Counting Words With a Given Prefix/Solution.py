class Solution:
    def prefixCount(self, words: list[str], pref: str) -> int:
        return sum(w.startswith(pref) for w in words)
