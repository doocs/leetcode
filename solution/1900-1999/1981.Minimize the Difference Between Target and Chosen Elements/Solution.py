class Solution:
    def minimizeTheDifference(self, mat: List[List[int]], target: int) -> int:
        f = [True]
        for row in mat:
            mx = max(row)
            g = [False] * (len(f) + mx)
            for x in row:
                for j in range(x, len(f) + x):
                    g[j] |= f[j - x]
            f = g
        return min(abs(j - target) for j, ok in enumerate(f) if ok)
