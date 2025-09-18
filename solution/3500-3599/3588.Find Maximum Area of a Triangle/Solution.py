class Solution:
    def maxArea(self, coords: List[List[int]]) -> int:
        def calc() -> int:
            mn, mx = inf, 0
            f = {}
            g = {}
            for x, y in coords:
                mn = min(mn, x)
                mx = max(mx, x)
                if x in f:
                    f[x] = min(f[x], y)
                    g[x] = max(g[x], y)
                else:
                    f[x] = g[x] = y
            ans = 0
            for x, y in f.items():
                d = g[x] - y
                ans = max(ans, d * max(mx - x, x - mn))
            return ans

        ans = calc()
        for c in coords:
            c[0], c[1] = c[1], c[0]
        ans = max(ans, calc())
        return ans if ans else -1
