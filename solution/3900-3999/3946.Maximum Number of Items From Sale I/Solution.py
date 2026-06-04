class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        f = [0] * (budget + 1)
        mn = inf
        for factor, price in items:
            mn = min(mn, price)
            cnt = sum(factor_j % factor == 0 for factor_j, _ in items)
            for j in range(budget, price - 1, -1):
                f[j] = max(f[j], f[j - price] + cnt)
        return max(x + (budget - i) // mn for i, x in enumerate(f))
