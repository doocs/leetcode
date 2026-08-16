class Solution:
    def minPenalty(self, period: int, lights: list[int], arrivalTime: list[int]) -> int:
        mx = max(lights)
        ans = 0
        for x in arrivalTime:
            r = x % period
            if r >= mx:
                ans = max(ans, period - r)
        return ans
