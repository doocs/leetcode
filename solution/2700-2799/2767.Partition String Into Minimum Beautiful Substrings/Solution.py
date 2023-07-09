class Solution:
    def minimumBeautifulSubstrings(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            if s[i] == "0":
                return inf
            x = 0
            ans = inf
            for j in range(i, n):
                x = x << 1 | int(s[j])
                if x in ss:
                    ans = min(ans, 1 + dfs(j + 1))
            return ans

        n = len(s)
        x = 1
        ss = {x}
        for i in range(n):
            x *= 5
            ss.add(x)
        ans = dfs(0)
        return -1 if ans == inf else ans
