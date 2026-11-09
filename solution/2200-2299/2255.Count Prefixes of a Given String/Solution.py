class Solution:
    def countPrefixes(self, words: list[str], s: str) -> int:
        return sum(s.startswith(w) for w in words)
