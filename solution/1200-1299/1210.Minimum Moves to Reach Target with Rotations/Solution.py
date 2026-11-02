class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        def move(i1, j1, i2, j2):
            if 0 <= i1 < n and 0 <= j1 < n and 0 <= i2 < n and 0 <= j2 < n:
                a, b = i1 * n + j1, i2 * n + j2
                status = 0 if i1 == i2 else 1
                if (a, status) not in vis and grid[i1][j1] == 0 and grid[i2][j2] == 0:
                    q.append((a, b))
                    vis.add((a, status))

        n = len(grid)
        target = (n * n - 2, n * n - 1)
        q = deque([(0, 1)])
        vis = {(0, 0)}
        ans = 0
        while q:
            for _ in range(len(q)):
                a, b = q.popleft()
                if (a, b) == target:
                    return ans
                i1, j1 = a // n, a % n
                i2, j2 = b // n, b % n
                move(i1, j1 + 1, i2, j2 + 1)
                move(i1 + 1, j1, i2 + 1, j2)
                if i1 == i2 and i1 + 1 < n and grid[i1 + 1][j2] == 0:
                    move(i1, j1, i1 + 1, j1)
                if j1 == j2 and j1 + 1 < n and grid[i2][j1 + 1] == 0:
                    move(i1, j1, i1, j1 + 1)
            ans += 1
        return -1
