class Solution:
    def minCuttingCost(self, n: int, m: int, k: int) -> int:
        x = max(n, m)
        return 0 if x <= k else k * (x - k)
