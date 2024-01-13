class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        left0, right0 = 0, s.count("0")
        n = len(s)
        ans = min(right0, n - right0)
        for i, c in enumerate(s, 1):
            x = int(c)
            right0 -= x ^ 1
            left0 += x ^ 1
            ans = min(ans, i - left0 + right0)
        return ans
