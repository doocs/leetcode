class Solution:
    def twoEggDrop(self, n: int) -> int:
        f = [0] + [inf] * n
        for i in range(1, n + 1):
            for j in range(1, i + 1):
                f[i] = min(f[i], 1 + max(j - 1, f[i - j]))
        return f[n]
