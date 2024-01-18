class Solution:
    def stoneGameII(self, piles: List[int]) -> int:
        @cache
        def dfs(i: int, m: int = 1) -> int:
            if i >= len(piles):
                return 0
            t = inf
            for x in range(1, m << 1 | 1):
                t = min(t, dfs(i + x, max(m, x)))
            return s[-1] - s[i] - t

        s = list(accumulate(piles, initial=0))
        return dfs(0)
