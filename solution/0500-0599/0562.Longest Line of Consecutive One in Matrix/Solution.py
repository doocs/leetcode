class Solution:
    def longestLine(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        a = [[0] * (n + 2) for _ in range(m + 2)]
        b = [[0] * (n + 2) for _ in range(m + 2)]
        c = [[0] * (n + 2) for _ in range(m + 2)]
        d = [[0] * (n + 2) for _ in range(m + 2)]
        ans = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                v = mat[i - 1][j - 1]
                if v:
                    a[i][j] = a[i - 1][j] + 1
                    b[i][j] = b[i][j - 1] + 1
                    c[i][j] = c[i - 1][j - 1] + 1
                    d[i][j] = d[i - 1][j + 1] + 1
                    ans = max(ans, a[i][j], b[i][j], c[i][j], d[i][j])
        return ans
