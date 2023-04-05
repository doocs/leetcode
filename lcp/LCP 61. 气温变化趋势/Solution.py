class Solution:
    def temperatureTrend(self, temperatureA: List[int], temperatureB: List[int]) -> int:
        ans = f = 0
        n = len(temperatureA)
        for i in range(n - 1):
            x = temperatureA[i + 1] - temperatureA[i]
            y = temperatureB[i + 1] - temperatureB[i]
            if x == y == 0 or x * y > 0:
                f += 1
                ans = max(ans, f)
            else:
                f = 0
        return ans
