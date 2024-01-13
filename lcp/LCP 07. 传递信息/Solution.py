class Solution:
    def numWays(self, n: int, relation: List[List[int]], k: int) -> int:
        f = [[0] * n for _ in range(k + 1)]
        f[0][0] = 1
        for i in range(1, k + 1):
            for a, b in relation:
                f[i][b] += f[i - 1][a]
        return f[-1][-1]
