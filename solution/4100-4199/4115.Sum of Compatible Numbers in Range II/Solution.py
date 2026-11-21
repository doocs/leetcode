class Solution:
    def sumOfGoodIntegers(self, n: int, k: int) -> int:
        @cache
        def dfs(i: int, lim: bool) -> tuple[int, int]:
            if i < 0:
                return 1, 0
            up = (num >> i & 1) if lim else 1
            cnt = s = 0
            for cur in range(up + 1):
                if (n >> i & 1) == 1 and cur == 1:
                    continue
                cnt1, s1 = dfs(i - 1, lim and cur == up)
                cnt += cnt1
                s += s1 + cnt1 * (cur << i) % mod
            return cnt, s % mod

        mod = 10**9 + 7
        lo, hi = max(1, n - k), n + k
        m = hi.bit_length()

        num = hi
        right = dfs(m - 1, True)[1]
        dfs.cache_clear()

        num = lo - 1
        left = dfs(m - 1, True)[1]
        dfs.cache_clear()

        return (right - left + mod) % mod
