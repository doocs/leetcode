class Solution:
    def minFallingPathSum(self, grid: List[List[int]]) -> int:
        f = g = 0
        fp = -1
        for row in grid:
            ff = gg = inf
            ffp = -1
            for j, v in enumerate(row):
                s = (g if j == fp else f) + v
                if s < ff:
                    gg = ff
                    ff = s
                    ffp = j
                elif s < gg:
                    gg = s
            f, g, fp = ff, gg, ffp
        return f
