class Solution:
    def findPoisonedDuration(self, timeSeries: list[int], duration: int) -> int:
        ans = duration
        for a, b in pairwise(timeSeries):
            ans += min(duration, b - a)
        return ans
