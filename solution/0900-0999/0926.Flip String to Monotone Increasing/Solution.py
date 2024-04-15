class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        tot = s.count("0")
        ans, cur = tot, 0
        for i, c in enumerate(s, 1):
            cur += int(c == "0")
            ans = min(ans, i - cur + tot - cur)
        return ans
