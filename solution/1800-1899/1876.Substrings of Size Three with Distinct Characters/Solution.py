class Solution:
    def countGoodSubstrings(self, s: str) -> int:
        count, n = 0, len(s)
        for i in range(n - 2):
            count += s[i] != s[i + 1] and s[i] != s[i + 2] and s[i + 1] != s[i + 2]
        return count
