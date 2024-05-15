class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = deque()
        cnt = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x == 1:
                    cnt += 1
                elif x == 2:
                    q.append((i, j))
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        while q and cnt:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                        grid[x][y] = 2
                        q.append((x, y))
                        cnt -= 1
        return -1 if cnt > 0 else ans
