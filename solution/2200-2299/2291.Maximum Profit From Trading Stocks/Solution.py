class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        f = [[0] * (budget + 1) for _ in range(len(present) + 1)]
        for i, w in enumerate(present, 1):
            for j in range(budget + 1):
                f[i][j] = f[i - 1][j]
                if j >= w and future[i - 1] > w:
                    f[i][j] = max(f[i][j], f[i - 1][j - w] + future[i - 1] - w)
        return f[-1][-1]
