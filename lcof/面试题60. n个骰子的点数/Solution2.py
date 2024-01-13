class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        f = [0] + [1] * 6
        for i in range(2, n + 1):
            g = [0] * (6 * i + 1)
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if 0 <= j - k < len(f):
                        g[j] += f[j - k]
            f = g
        m = pow(6, n)
        return [f[j] / m for j in range(n, 6 * n + 1)]
