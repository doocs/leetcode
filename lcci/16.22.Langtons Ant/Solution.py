class Solution:
    def printKMoves(self, K: int) -> List[str]:
        x1 = y1 = x2 = y2 = 0
        dirs = (0, 1, 0, -1, 0)
        d = "RDLU"
        x = y = 0
        p = 0
        black = set()
        for _ in range(K):
            if (x, y) in black:
                black.remove((x, y))
                p = (p + 3) % 4
            else:
                black.add((x, y))
                p = (p + 1) % 4
            x += dirs[p]
            y += dirs[p + 1]
            x1 = min(x1, x)
            y1 = min(y1, y)
            x2 = max(x2, x)
            y2 = max(y2, y)
        m, n = x2 - x1 + 1, y2 - y1 + 1
        g = [["_"] * n for _ in range(m)]
        for i, j in black:
            g[i - x1][j - y1] = "X"
        g[x - x1][y - y1] = d[p]
        return ["".join(row) for row in g]
