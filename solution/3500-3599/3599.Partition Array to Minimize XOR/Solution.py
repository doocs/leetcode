min = lambda a, b: a if a < b else b
max = lambda a, b: a if a > b else b


class Solution:
    def minXor(self, nums: List[int], k: int) -> int:
        n = len(nums)
        g = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            g[i] = g[i - 1] ^ x

        f = [[inf] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i in range(1, n + 1):
            for j in range(1, min(i, k) + 1):
                for h in range(j - 1, i):
                    f[i][j] = min(f[i][j], max(f[h][j - 1], g[i] ^ g[h]))
        return f[n][k]
