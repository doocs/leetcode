class Solution:
    def countFancy(self, l: int, r: int) -> int:
        def check(s: int) -> bool:
            if s < 100:
                return s % 11 != 0
            return 1 < s // 10 % 10 < s % 10

        @cache
        def dfs(pos: int, s: int, prev: int, st: int, lim: bool) -> int:
            if pos >= len(num):
                if st != 3:
                    return 1
                return int(check(s))
            up = int(num[pos]) if lim else 9
            res = 0
            for i in range(up + 1):
                nxt_st = st
                if st == 0:
                    if prev == 0:
                        nxt_st = 0
                    elif i > prev:
                        nxt_st = 1
                    elif i < prev:
                        nxt_st = 2
                    else:
                        nxt_st = 3
                elif st == 1:
                    if i > prev:
                        nxt_st = 1
                    else:
                        nxt_st = 3
                elif st == 2:
                    if i < prev:
                        nxt_st = 2
                    else:
                        nxt_st = 3
                else:
                    nxt_st = 3
                res += dfs(pos + 1, s + i, i, nxt_st, lim and i == up)
            return res

        num = str(l - 1)
        a = dfs(0, 0, 0, 0, True)
        dfs.cache_clear()
        num = str(r)
        b = dfs(0, 0, 0, 0, True)
        return b - a
