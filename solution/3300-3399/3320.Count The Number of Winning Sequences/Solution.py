class Solution:
    def countWinningSequences(self, s: str) -> int:
        def calc(x: int, y: int) -> int:
            if x == y:
                return 0
            if x < y:
                return 1 if x == 0 and y == 2 else -1
            return -1 if x == 2 and y == 0 else 1

        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if len(s) - i <= j:
                return 0
            if i >= len(s):
                return int(j < 0)
            res = 0
            for l in range(3):
                if l == k:
                    continue
                res = (res + dfs(i + 1, j + calc(d[s[i]], l), l)) % mod
            return res

        mod = 10**9 + 7
        d = {"F": 0, "W": 1, "E": 2}
        ans = dfs(0, 0, -1)
        dfs.cache_clear()
        return ans
