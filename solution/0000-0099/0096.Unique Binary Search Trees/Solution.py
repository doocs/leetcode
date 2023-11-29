class Solution:
    def numTrees(self, n: int) -> int:
        f = [1] + [0] * n
        for i in range(n + 1):
            for j in range(i):
                f[i] += f[j] * f[i - j - 1]
        return f[n]
