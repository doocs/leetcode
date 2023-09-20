class Solution:
    def numWays(self, n: int, relation: List[List[int]], k: int) -> int:
        f = [1] + [0] * (n - 1)
        for _ in range(k):
            g = [0] * n
            for a, b in relation:
                g[b] += f[a]
            f = g
        return f[-1]
