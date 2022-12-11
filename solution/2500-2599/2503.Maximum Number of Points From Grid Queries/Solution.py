class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        m, n = len(grid), len(grid[0])
        qs = sorted((v, i) for i, v in enumerate(queries))
        ans = [0] * len(qs)
        vis = [[False] * n for _ in range(m)]
        vis[0][0] = True
        cnt = 0
        q = [(grid[0][0], 0, 0)]
        dirs = (-1, 0, 1, 0, -1)
        for v, k in qs:
            while q and q[0][0] < v:
                cnt += 1
                _, i, j = heappop(q)
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and not vis[x][y]:
                        vis[x][y] = True
                        heappush(q, (grid[x][y], x, y))
            ans[k] = cnt
        return ans
