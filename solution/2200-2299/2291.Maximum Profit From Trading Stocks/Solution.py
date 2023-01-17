class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        f = [0] * (budget + 1)
        for a, b in zip(present, future):
            for j in range(budget, a - 1, -1):
                f[j] = max(f[j], f[j - a] + b - a)
        return f[-1]
