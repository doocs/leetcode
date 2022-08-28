class Solution:
    def matrixRankTransform(self, matrix: List[List[int]]) -> List[List[int]]:
        def find(x):
            if p.setdefault(x, x) != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            p[find(a)] = find(b)

        m, n = len(matrix), len(matrix[0])
        d = defaultdict(list)
        for i, row in enumerate(matrix):
            for j, v in enumerate(row):
                d[v].append((i, j))
        row_max = [0] * m
        col_max = [0] * n
        ans = [[0] * n for _ in range(m)]
        for v in sorted(d):
            p, rank = {}, defaultdict(int)
            for i, j in d[v]:
                union(i, j + 500)
            for i, j in d[v]:
                rank[find(i)] = max(rank[find(i)], row_max[i], col_max[j])
            for i, j in d[v]:
                ans[i][j] = row_max[i] = col_max[j] = 1 + rank[find(i)]
        return ans
