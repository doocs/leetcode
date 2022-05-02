class Solution:
    def countPrefixes(self, words: List[str], s: str) -> int:
        return sum(word == s[: len(word)] for word in words)
