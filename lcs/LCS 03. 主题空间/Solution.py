class Solution:
    def largestArea(self, grid: List[str]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1 or grid[i][j] == '0':
                    p[find(i * n + j)] = find(m * n)
                else:
                    for x, y in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                        if grid[i + x][j + y] == '0' or grid[i][j] == grid[i + x][j + y]:
                            p[find(i * n + j)] = find((i + x) * n + j + y)

        mp = defaultdict(int)
        res = 0
        for i in range(m):
            for j in range(n):
                root = find(i * n + j)
                if root != find(m * n):
                    mp[root] += 1
                    res = max(res, mp[root])
        return res
