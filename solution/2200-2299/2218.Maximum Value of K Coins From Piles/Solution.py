class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        n = len(piles)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i, nums in enumerate(piles, 1):
            s = list(accumulate(nums, initial=0))
            for j in range(k + 1):
                for h, w in enumerate(s):
                    if j < h:
                        break
                    f[i][j] = max(f[i][j], f[i - 1][j - h] + w)
        return f[n][k]
