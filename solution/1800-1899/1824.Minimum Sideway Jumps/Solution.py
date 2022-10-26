class Solution:
    def minSideJumps(self, obstacles: List[int]) -> int:
        f = [1, 0, 1]
        for v in obstacles[1:]:
            g = [inf] * 3
            for j in range(3):
                if v != j + 1:
                    g[j] = f[j]
            if v != 1:
                g[0] = min(g[0], min(g[1], g[2]) + 1)
            if v != 2:
                g[1] = min(g[1], min(g[0], g[2]) + 1)
            if v != 3:
                g[2] = min(g[2], min(g[0], g[1]) + 1)
            f = g
        return min(f)
