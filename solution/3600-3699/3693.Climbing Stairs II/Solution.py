class Solution:
    def climbStairs(self, n: int, costs: List[int]) -> int:
        n = len(costs)
        f = [inf] * (n + 1)
        f[0] = 0
        for i, x in enumerate(costs, 1):
            for j in range(i - 3, i):
                if j >= 0:
                    f[i] = min(f[i], f[j] + x + (i - j) ** 2)
        return f[n]
