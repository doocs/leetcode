class Solution:
    def getProbability(self, balls: List[int]) -> float:
        @cache
        def dfs(i: int, j: int, diff: int) -> float:
            if i >= k:
                return 1 if j == 0 and diff == 0 else 0
            if j < 0:
                return 0
            ans = 0
            for x in range(balls[i] + 1):
                y = 1 if x == balls[i] else (-1 if x == 0 else 0)
                ans += dfs(i + 1, j - x, diff + y) * comb(balls[i], x)
            return ans

        n = sum(balls) >> 1
        k = len(balls)
        return dfs(0, n, 0) / comb(n << 1, n)
