class Solution:
    def stoneGameII(self, piles: List[int]) -> int:
        @cache
        def dfs(i, m):
            if m * 2 >= n - i:
                return s[n] - s[i]
            return max(
                s[n] - s[i] - dfs(i + x, max(m, x)) for x in range(1, m << 1 | 1)
            )

        n = len(piles)
        s = list(accumulate(piles, initial=0))
        return dfs(0, 1)
