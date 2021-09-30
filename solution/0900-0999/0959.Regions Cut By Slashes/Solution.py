class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        n = len(grid)
        p = list(range(n * n * 4))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            for j in range(n):
                idx = i * n + j
                if i < n - 1:
                    p[find(idx * 4 + 2)] = find((idx + n) * 4)
                if j < n - 1:
                    p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3)

                if grid[i][j] == '/':
                    p[find(idx * 4)] = find(idx * 4 + 3)
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2)
                elif grid[i][j] == '\\':
                    p[find(idx * 4)] = find(idx * 4 + 1)
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3)
                else:
                    p[find(idx * 4)] = find(idx * 4 + 1)
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2)
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3)
        s = set()
        for i in range(len(p)):
            s.add(find(i))
        return len(s)
