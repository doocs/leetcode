class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        ans, mx = 0, values[0]
        for j in range(1, len(values)):
            ans = max(ans, values[j] - j + mx)
            mx = max(mx, values[j] + j)
        return ans
