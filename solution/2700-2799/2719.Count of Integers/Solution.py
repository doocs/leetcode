class Solution:
    def count(self, num1: str, num2: str, min_sum: int, max_sum: int) -> int:
        @cache
        def dfs(pos: int, s: int, limit: bool) -> int:
            if pos >= len(num):
                return 1 if min_sum <= s <= max_sum else 0
            up = int(num[pos]) if limit else 9
            return (
                sum(dfs(pos + 1, s + i, limit and i == up) for i in range(up + 1)) % mod
            )

        mod = 10**9 + 7
        num = num2
        ans = dfs(0, 0, True)
        dfs.cache_clear()
        num = str(int(num1) - 1)
        ans -= dfs(0, 0, True)
        return ans % mod
