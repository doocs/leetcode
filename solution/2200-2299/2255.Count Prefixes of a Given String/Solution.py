class Solution:
    def countPrefixes(self, words: List[str], s: str) -> int:
        return sum(s.startswith(w) for w in words)
