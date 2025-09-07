class Solution:
    def maxCollectedFruits(self, fruits: List[List[int]]) -> int:
        n = len(fruits)
        f = [[-inf] * n for _ in range(n)]
        f[0][n - 1] = fruits[0][n - 1]
        for i in range(1, n):
            for j in range(i + 1, n):
                f[i][j] = max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j]
                if j + 1 < n:
                    f[i][j] = max(f[i][j], f[i - 1][j + 1] + fruits[i][j])
        f[n - 1][0] = fruits[n - 1][0]
        for j in range(1, n):
            for i in range(j + 1, n):
                f[i][j] = max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j]
                if i + 1 < n:
                    f[i][j] = max(f[i][j], f[i + 1][j - 1] + fruits[i][j])
        return sum(fruits[i][i] for i in range(n)) + f[n - 2][n - 1] + f[n - 1][n - 2]
