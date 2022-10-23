class Solution:
    def minCost(self, nums: List[int], cost: List[int]) -> int:
        arr = sorted(zip(nums, cost))
        n = len(arr)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        for i in range(1, n + 1):
            a, b = arr[i - 1]
            f[i] = f[i - 1] + a * b
            g[i] = g[i - 1] + b
        ans = inf
        for i in range(1, n + 1):
            a = arr[i - 1][0]
            l = a * g[i - 1] - f[i - 1]
            r = f[n] - f[i] - a * (g[n] - g[i])
            ans = min(ans, l + r)
        return ans
