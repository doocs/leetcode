class Solution:
    def countUnguarded(
        self, m: int, n: int, guards: List[List[int]], walls: List[List[int]]
    ) -> int:
        g = [[None] * n for _ in range(m)]
        for r, c in guards:
            g[r][c] = 'g'
        for r, c in walls:
            g[r][c] = 'w'
        for i, j in guards:
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i, j
                while (
                    0 <= x + a < m
                    and 0 <= y + b < n
                    and g[x + a][y + b] != 'w'
                    and g[x + a][y + b] != 'g'
                ):
                    x, y = x + a, y + b
                    g[x][y] = 'v'
        return sum(not v for row in g for v in row)
