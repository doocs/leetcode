class Solution:
    def minimumMoves(self, arr: List[int]) -> int:
        n = len(arr)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if i + 1 == j:
                    f[i][j] = 1 if arr[i] == arr[j] else 2
                else:
                    t = f[i + 1][j - 1] if arr[i] == arr[j] else inf
                    for k in range(i, j):
                        t = min(t, f[i][k] + f[k + 1][j])
                    f[i][j] = t
        return f[0][n - 1]
