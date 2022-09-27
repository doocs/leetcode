class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        g = [[0] * 110 for _ in range(110)]
        g[0][0] = poured
        for i in range(query_row + 1):
            for j in range(i + 1):
                if g[i][j] > 1:
                    half = (g[i][j] - 1) / 2
                    g[i][j] = 1
                    g[i + 1][j] += half
                    g[i + 1][j + 1] += half
        return g[query_row][query_glass]
