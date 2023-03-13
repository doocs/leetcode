class Solution:
    def numOfStrings(self, patterns: List[str], word: str) -> int:
        return sum(p in word for p in patterns)
