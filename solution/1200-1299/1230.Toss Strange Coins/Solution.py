class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        n = len(prob)
        f = [[0] * (target + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i, p in enumerate(prob, 1):
            for j in range(min(i, target) + 1):
                f[i][j] = (1 - p) * f[i - 1][j]
                if j:
                    f[i][j] += p * f[i - 1][j - 1]
        return f[n][target]
