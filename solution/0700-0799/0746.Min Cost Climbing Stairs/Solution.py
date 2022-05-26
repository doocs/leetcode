class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        pre = cur = 0
        n = len(cost)
        for i in range(1, n):
            t = min(cost[i] + cur, cost[i - 1] + pre)
            pre, cur = cur, t
        return cur
