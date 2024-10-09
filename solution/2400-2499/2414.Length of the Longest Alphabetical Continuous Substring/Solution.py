class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        ans = cnt = 1
        for x, y in pairwise(map(ord, s)):
            if y - x == 1:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 1
        return ans
