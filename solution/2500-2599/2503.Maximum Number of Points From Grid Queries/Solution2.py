class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        m, n = len(grid), len(grid[0])
        arr = sorted((grid[i][j], i, j) for i in range(m) for j in range(n))
        k = len(queries)
        ans = [0] * k
        p = list(range(m * n))
        size = [1] * len(p)
        j = 0
        for i, v in sorted(enumerate(queries), key=lambda x: x[1]):
            while j < len(arr) and arr[j][0] < v:
                _, a, b = arr[j]
                for x, y in pairwise((-1, 0, 1, 0, -1)):
                    c, d = a + x, b + y
                    if 0 <= c < m and 0 <= d < n and grid[c][d] < v:
                        union(a * n + b, c * n + d)
                j += 1
            if grid[0][0] < v:
                ans[i] = size[find(0)]
        return ans
