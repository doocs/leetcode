class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j]:
                    g[i][j] = 1 if j == 0 else 1 + g[i][j - 1]
        ans = 0
        for i in range(m):
            for j in range(n):
                col = inf
                for k in range(i, -1, -1):
                    col = min(col, g[k][j])
                    ans += col
        return ans
