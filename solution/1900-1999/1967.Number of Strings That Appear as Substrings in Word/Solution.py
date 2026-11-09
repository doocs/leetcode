class Solution:
    def numOfStrings(self, patterns: list[str], word: str) -> int:
        return sum(p in word for p in patterns)
