class Solution:
    def maxPower(self, s: str) -> int:
        ans = t = 0
        for i, c in enumerate(s):
            if i == 0 or c == s[i - 1]:
                t += 1
            else:
                t = 1
            ans = max(ans, t)
        return ans
