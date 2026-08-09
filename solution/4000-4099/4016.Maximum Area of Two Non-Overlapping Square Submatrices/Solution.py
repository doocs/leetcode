class Solution:
    def maxArea(self, mat: list[list[int]]) -> int:
        def calc(mat: list[list[int]]) -> int:
            m, n = len(mat), len(mat[0])

            f = [[0] * (n + 1) for _ in range(m + 1)]
            g = [0] * (m + 1)
            suf = [0] * (m + 1)
            for i in range(m - 1, 0, -1):
                for j in range(n - 1, -1, -1):
                    if mat[i][j]:
                        f[i][j] = min(f[i + 1][j], f[i][j + 1], f[i + 1][j + 1]) + 1
                        g[i] = max(g[i], f[i][j])
                suf[i] = max(suf[i + 1], g[i])

            f = [[0] * (n + 1) for _ in range(m + 1)]
            g = [0] * (m + 1)
            pre = [0] * (m + 1)
            for i in range(1, m + 1):
                for j in range(1, n + 1):
                    if mat[i - 1][j - 1]:
                        f[i][j] = min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1
                        g[i] = max(g[i], f[i][j])

                pre[i] = max(pre[i - 1], g[i])

            ans = 0
            for i in range(1, m):
                t = min(pre[i], suf[i])
                ans = max(ans, t * t)
            return ans

        def transpose(mat: list[list[int]]) -> list[list[int]]:
            m, n = len(mat), len(mat[0])
            ans = [[0] * m for _ in range(n)]
            for i in range(m):
                for j in range(n):
                    ans[j][i] = mat[i][j]
            return ans

        return max(calc(mat), calc(transpose(mat)))
