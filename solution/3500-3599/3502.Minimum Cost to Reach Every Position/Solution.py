class Solution:
    def minCosts(self, cost: list[int]) -> list[int]:
        n = len(cost)
        ans = [0] * n
        mi = cost[0]
        for i, c in enumerate(cost):
            mi = min(mi, c)
            ans[i] = mi
        return ans
