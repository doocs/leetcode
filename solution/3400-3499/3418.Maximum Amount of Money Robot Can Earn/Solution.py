class Solution:
    def maximumAmount(self, coins: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= m or j >= n:
                return -inf
            if i == m - 1 and j == n - 1:
                return max(coins[i][j], 0) if k else coins[i][j]
            ans = coins[i][j] + max(dfs(i + 1, j, k), dfs(i, j + 1, k))
            if coins[i][j] < 0 and k:
                ans = max(ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1))
            return ans

        m, n = len(coins), len(coins[0])
        return dfs(0, 0, 2)
