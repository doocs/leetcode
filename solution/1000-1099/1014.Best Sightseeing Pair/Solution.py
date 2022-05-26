class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        res, mx = 0, values[0]
        for i in range(1, len(values)):
            res = max(res, values[i] - i + mx)
            mx = max(mx, values[i] + i)
        return res
