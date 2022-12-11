class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        m, n = len(grid), len(grid[0])
        qs = sorted((v, i) for i, v in enumerate(queries))
        ans = [0] * len(qs)
        q = [(grid[0][0], 0, 0)]
        cnt = 0
        vis = [[False] * n for _ in range(m)]
        vis[0][0] = True
        for v, k in qs:
            while q and q[0][0] < v:
                _, i, j = heappop(q)
                cnt += 1
                for a, b in pairwise((-1, 0, 1, 0, -1)):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and not vis[x][y]:
                        heappush(q, (grid[x][y], x, y))
                        vis[x][y] = True
            ans[k] = cnt
        return ans
