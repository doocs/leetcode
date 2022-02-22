class Solution:
    def minCost(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dirs = [[0, 0], [0, 1], [0, -1], [1, 0], [-1, 0]]
        q = deque([(0, 0, 0)])
        vis = set()
        while q:
            i, j, d = q.popleft()
            if (i, j) in vis:
                continue
            vis.add((i, j))
            if i == m - 1 and j == n - 1:
                return d
            for k in range(1, 5):
                x, y = i + dirs[k][0], j + dirs[k][1]
                if 0 <= x < m and 0 <= y < n:
                    if grid[i][j] == k:
                        q.appendleft((x, y, d))
                    else:
                        q.append((x, y, d + 1))
        return -1
