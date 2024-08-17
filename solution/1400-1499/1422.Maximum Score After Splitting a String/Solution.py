class Solution:
    def maxScore(self, s: str) -> int:
        l, r = 0, s.count("1")
        ans = 0
        for x in s[:-1]:
            l += int(x) ^ 1
            r -= int(x)
            ans = max(ans, l + r)
        return ans
