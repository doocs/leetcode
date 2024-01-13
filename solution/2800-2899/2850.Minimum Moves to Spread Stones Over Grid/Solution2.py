class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        def cal(a: tuple, b: tuple) -> int:
            return abs(a[0] - b[0]) + abs(a[1] - b[1])

        left, right = [], []
        for i in range(3):
            for j in range(3):
                if grid[i][j] == 0:
                    left.append((i, j))
                else:
                    for _ in range(grid[i][j] - 1):
                        right.append((i, j))

        n = len(left)
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            k = i.bit_count()
            for j in range(n):
                if i >> j & 1:
                    f[i] = min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]))
        return f[-1]
