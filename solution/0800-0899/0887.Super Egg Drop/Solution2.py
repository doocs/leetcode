class Solution:
    def superEggDrop(self, k: int, n: int) -> int:
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            f[i][1] = i
        for i in range(1, n + 1):
            for j in range(2, k + 1):
                l, r = 1, i
                while l < r:
                    mid = (l + r + 1) >> 1
                    a, b = f[mid - 1][j - 1], f[i - mid][j]
                    if a <= b:
                        l = mid
                    else:
                        r = mid - 1
                f[i][j] = max(f[l - 1][j - 1], f[i - l][j]) + 1
        return f[n][k]
