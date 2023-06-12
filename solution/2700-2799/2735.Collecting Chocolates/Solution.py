class Solution:
    def minCost(self, nums: List[int], x: int) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        for i, v in enumerate(nums):
            f[i][0] = v
            for j in range(1, n):
                f[i][j] = min(f[i][j - 1], nums[(i + j) % n])
        ans = inf
        for j in range(n):
            cost = sum(f[i][j] for i in range(n)) + x * j
            ans = min(ans, cost)
        return ans
