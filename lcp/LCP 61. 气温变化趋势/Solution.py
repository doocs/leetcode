class Solution:
    def temperatureTrend(self, temperatureA: List[int], temperatureB: List[int]) -> int:
        ans = f = 0
        for (a1, b1), (a2, b2) in pairwise(zip(temperatureA, temperatureB)):
            x, y = a2 - a1, b2 - b1
            if x == y == 0 or x * y > 0:
                f += 1
                ans = max(ans, f)
            else:
                f = 0
        return ans
