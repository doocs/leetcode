class Solution:
    def findSubstringInWraproundString(self, p: str) -> int:
        dp = [0] * 26
        k = 0
        for i, c in enumerate(p):
            if i and (ord(c) - ord(p[i - 1])) % 26 == 1:
                k += 1
            else:
                k = 1
            idx = ord(c) - ord('a')
            dp[idx] = max(dp[idx], k)
        return sum(dp)
