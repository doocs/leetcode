class Solution:
    def countBalanced(self, low: int, high: int) -> int:
        @cache
        def dfs(pos: int, diff: int, lim: int) -> int:
            if pos >= len(num):
                return 1 if diff == 0 else 0
            res = 0
            up = int(num[pos]) if lim else 9
            for i in range(up + 1):
                res += dfs(
                    pos + 1, diff + i * (1 if pos % 2 == 0 else -1), lim and i == up
                )
            return res

        if high < 11:
            return 0
        low = max(low, 11)
        num = str(low - 1)
        a = dfs(0, 0, True)
        dfs.cache_clear()
        num = str(high)
        b = dfs(0, 0, True)
        return b - a
