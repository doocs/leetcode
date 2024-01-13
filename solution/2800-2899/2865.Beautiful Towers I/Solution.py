class Solution:
    def maximumSumOfHeights(self, maxHeights: List[int]) -> int:
        ans, n = 0, len(maxHeights)
        for i, x in enumerate(maxHeights):
            y = t = x
            for j in range(i - 1, -1, -1):
                y = min(y, maxHeights[j])
                t += y
            y = x
            for j in range(i + 1, n):
                y = min(y, maxHeights[j])
                t += y
            ans = max(ans, t)
        return ans
