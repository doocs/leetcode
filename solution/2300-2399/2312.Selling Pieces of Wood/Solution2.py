class Solution:
    def sellingWood(self, m: int, n: int, prices: List[List[int]]) -> int:
        d = defaultdict(dict)
        for h, w, p in prices:
            d[h][w] = p
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                f[i][j] = d[i].get(j, 0)
                for k in range(1, i):
                    f[i][j] = max(f[i][j], f[k][j] + f[i - k][j])
                for k in range(1, j):
                    f[i][j] = max(f[i][j], f[i][k] + f[i][j - k])
        return f[m][n]
