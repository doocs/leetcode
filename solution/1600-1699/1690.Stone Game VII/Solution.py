class Solution:
    def stoneGameVII(self, stones: List[int]) -> int:
        @cache
        def dfs(i, j):
            if i > j:
                return 0
            a = s[j + 1] - s[i + 1] - dfs(i + 1, j)
            b = s[j] - s[i] - dfs(i, j - 1)
            return max(a, b)

        s = list(accumulate(stones, initial=0))
        ans = dfs(0, len(stones) - 1)
        dfs.cache_clear()
        return ans
