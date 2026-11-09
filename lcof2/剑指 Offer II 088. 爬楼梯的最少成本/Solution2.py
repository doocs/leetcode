class Solution:
    def minCostClimbingStairs(self, cost: list[int]) -> int:
        a = b = 0
        for i in range(1, len(cost)):
            a, b = b, min(a + cost[i - 1], b + cost[i])
        return b
