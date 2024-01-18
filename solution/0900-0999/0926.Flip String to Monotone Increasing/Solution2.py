class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        presum = [0] * (n + 1)
        for i, c in enumerate(s):
            presum[i + 1] = presum[i] + int(c)
        ans = presum[-1]
        for i in range(n):
            ans = min(ans, presum[i] + n - i - (presum[-1] - presum[i]))
        return ans
