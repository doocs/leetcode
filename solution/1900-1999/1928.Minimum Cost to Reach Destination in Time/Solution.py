class Solution:
    def minCost(
        self, maxTime: int, edges: List[List[int]], passingFees: List[int]
    ) -> int:
        m, n = maxTime, len(passingFees)
        f = [[inf] * n for _ in range(m + 1)]
        f[0][0] = passingFees[0]
        for i in range(1, m + 1):
            for x, y, t in edges:
                if t <= i:
                    f[i][x] = min(f[i][x], f[i - t][y] + passingFees[x])
                    f[i][y] = min(f[i][y], f[i - t][x] + passingFees[y])
        ans = min(f[i][n - 1] for i in range(m + 1))
        return ans if ans < inf else -1
