class Solution:
    def numTimesAllBlue(self, flips: List[int]) -> int:
        ans = mx = 0
        for i, v in enumerate(flips, 1):
            mx = max(mx, v)
            if mx == i:
                ans += 1
        return ans
