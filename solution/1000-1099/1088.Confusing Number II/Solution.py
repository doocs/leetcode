class Solution:
    def confusingNumberII(self, n: int) -> int:
        def check(x: int) -> bool:
            y, t = 0, x
            while t:
                t, v = divmod(t, 10)
                y = y * 10 + d[v]
            return x != y

        def dfs(pos: int, limit: bool, x: int) -> int:
            if pos >= len(s):
                return int(check(x))
            up = int(s[pos]) if limit else 9
            ans = 0
            for i in range(up + 1):
                if d[i] != -1:
                    ans += dfs(pos + 1, limit and i == up, x * 10 + i)
            return ans

        d = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6]
        s = str(n)
        return dfs(0, True, 0)
