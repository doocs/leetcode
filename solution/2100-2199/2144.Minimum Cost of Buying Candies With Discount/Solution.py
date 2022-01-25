class Solution:
    def minimumCost(self, cost: List[int]) -> int:
        cost.sort()
        ans, n = 0, len(cost)
        for i in range(n - 1, -1, -3):
            ans += cost[i]
            if i >= 1:
                ans += cost[i - 1]
        return ans
