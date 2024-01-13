class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            p[i][j] = root
            cnt[root] += 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] and p[x][y] == 0:
                    dfs(x, y)

        n = len(grid)
        cnt = Counter()
        p = [[0] * n for _ in range(n)]
        root = 0
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v and p[i][j] == 0:
                    root += 1
                    dfs(i, j)

        ans = max(cnt.values(), default=0)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    t = 1
                    vis = set()
                    for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n:
                            root = p[x][y]
                            if root not in vis:
                                vis.add(root)
                                t += cnt[root]
                    ans = max(ans, t)
        return ans
