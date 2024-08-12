class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        n = len(nums)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        s = list(accumulate(nums, initial=0))
        for i in range(1, n + 1):
            f[i][1] = s[i] / i
            for j in range(2, min(i + 1, k + 1)):
                for h in range(i):
                    f[i][j] = max(f[i][j], f[h][j - 1] + (s[i] - s[h]) / (i - h))
        return f[n][k]
