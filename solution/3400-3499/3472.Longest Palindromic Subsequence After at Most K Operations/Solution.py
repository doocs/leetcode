class Solution:
    def longestPalindromicSubsequence(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i > j:
                return 0
            if i == j:
                return 1
            res = max(dfs(i + 1, j, k), dfs(i, j - 1, k))
            d = abs(s[i] - s[j])
            t = min(d, 26 - d)
            if t <= k:
                res = max(res, dfs(i + 1, j - 1, k - t) + 2)
            return res

        s = list(map(ord, s))
        n = len(s)
        ans = dfs(0, n - 1, k)
        dfs.cache_clear()
        return ans
