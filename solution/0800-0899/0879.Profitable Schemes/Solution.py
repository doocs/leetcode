class Solution:
    def profitableSchemes(
        self, n: int, minProfit: int, group: List[int], profit: List[int]
    ) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= len(group):
                return 1 if k == minProfit else 0
            ans = dfs(i + 1, j, k)
            if j + group[i] <= n:
                ans += dfs(i + 1, j + group[i], min(k + profit[i], minProfit))
            return ans % (10**9 + 7)

        return dfs(0, 0, 0)
