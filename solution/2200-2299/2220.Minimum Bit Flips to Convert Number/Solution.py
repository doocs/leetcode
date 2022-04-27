class Solution:
    def minBitFlips(self, start: int, goal: int) -> int:
        t = start ^ goal
        ans = 0
        while t:
            ans += t & 1
            t >>= 1
        return ans
