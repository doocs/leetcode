class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        def check(a, b):
            if (a, b) not in vis:
                vis.add((a, b))
                q.append((a, b))

        n = len(grid)
        target = (n * n - 2, n * n - 1)
        q = deque([(0, 1)])
        vis = {(0, 1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                a, b = q.popleft()
                if (a, b) == target:
                    return ans
                i1, j1 = a // n, a % n
                i2, j2 = b // n, b % n
                if (
                    j1 + 1 < n
                    and j2 + 1 < n
                    and grid[i1][j1 + 1] == 0
                    and grid[i2][j2 + 1] == 0
                ):
                    check(i1 * n + j1 + 1, i2 * n + j2 + 1)
                    if j1 == j2:
                        check(a, i1 * n + j2 + 1)
                if (
                    i1 + 1 < n
                    and i2 + 1 < n
                    and grid[i1 + 1][j1] == 0
                    and grid[i2 + 1][j2] == 0
                ):
                    check((i1 + 1) * n + j1, (i2 + 1) * n + j2)
                    if i1 == i2:
                        check(a, (i2 + 1) * n + j1)
            ans += 1
        return -1
