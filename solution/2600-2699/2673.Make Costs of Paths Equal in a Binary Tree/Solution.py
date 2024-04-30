class Solution:
    def minIncrements(self, n: int, cost: List[int]) -> int:
        ans = 0
        for i in range(n >> 1, 0, -1):
            l, r = i << 1, i << 1 | 1
            ans += abs(cost[l - 1] - cost[r - 1])
            cost[i - 1] += max(cost[l - 1], cost[r - 1])
        return ans
