class Solution:
    def colorGrid(self, n: int, m: int, sources: list[list[int]]) -> list[list[int]]:
        ans = [[0] * m for _ in range(n)]
        q = sources
        dirs = (-1, 0, 1, 0, -1)
        for r, c, color in q:
            ans[r][c] = color
        while q:
            vis = defaultdict(int)
            for r, c, color in q:
                for a, b in pairwise(dirs):
                    x, y = r + a, c + b
                    if not 0 <= x < n or not 0 <= y < m or ans[x][y]:
                        continue
                    vis[(x, y)] = max(vis[(x, y)], color)
            q.clear()
            for (x, y), color in vis.items():
                q.append((x, y, color))
                ans[x][y] = color
        return ans
