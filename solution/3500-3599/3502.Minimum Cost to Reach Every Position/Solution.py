class Solution:
    def minCosts(self, cost: List[int]) -> List[int]:
        n = len(cost)
        ans = [0] * n
        mi = cost[0]
        for i, c in enumerate(cost):
            mi = min(mi, c)
            ans[i] = mi
        return ans
