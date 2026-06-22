class Solution:
    def createGrid(self, m: int, n: int) -> list[str]:
        g = [["#"] * n for _ in range(m)]
        g[0] = ["."] * n
        for i in range(m):
            g[i][-1] = "."
        return ["".join(row) for row in g]
