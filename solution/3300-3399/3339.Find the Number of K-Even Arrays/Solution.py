class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if j < 0:
                return 0
            if i >= n:
                return int(j == 0)
            return (
                cnt1 * dfs(i + 1, j, 1) + cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0)
            ) % mod

        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        ans = dfs(0, k, 1)
        dfs.cache_clear()
        return ans
