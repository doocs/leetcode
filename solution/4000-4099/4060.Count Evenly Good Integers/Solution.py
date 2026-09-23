class Solution:
    def countEvenlyGoodIntegers(self, l: int, r: int) -> int:
        @cache
        def dfs(pos: int, st: int, lim: bool) -> int:
            if pos >= len(s):
                return st ^ 1
            up = int(s[pos]) if lim else 9
            return sum(
                dfs(pos + 1, (st + (i & 1 ^ 1)) % 2, lim and i == up)
                for i in range(up + 1)
            )

        s = str(l - 1)
        a = dfs(0, 0, True)
        dfs.cache_clear()
        s = str(r)
        b = dfs(0, 0, True)
        return b - a
