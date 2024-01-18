class Solution:
    def minChanges(self, s: str) -> int:
        return sum(s[i] != s[i - 1] for i in range(1, len(s), 2))
