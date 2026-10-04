class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        f = [0] * (n + 2)
        for i in range(n - 1, -1, -1):
            f[i] = cost[i] + min(f[i + 1], f[i + 2])
        return min(f[0], f[1])
