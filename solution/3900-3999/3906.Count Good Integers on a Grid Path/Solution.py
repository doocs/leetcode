class Solution:
    def countGoodIntegersOnPath(self, l: int, r: int, directions: str) -> int:
        key = [False] * 16
        row, col = 0, 0
        key[0] = True
        for c in directions:
            if c == "D":
                row += 1
            else:
                col += 1
            key[row * 4 + col] = True

        s = ""

        @cache
        def dfs(pos, last, lim):
            if pos == 16:
                return 1

            res = 0
            start = last if key[pos] else 0
            end = int(s[pos]) if lim else 9

            for i in range(start, end + 1):
                res += dfs(pos + 1, i if key[pos] else last, lim and (i == end))

            return res

        def calc(x):
            nonlocal s
            if x < 0:
                return 0
            s = str(x).zfill(16)
            dfs.cache_clear()
            return dfs(0, 0, True)

        return calc(r) - calc(l - 1)
