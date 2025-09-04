mx = 10**5 + 1
g = [[] for _ in range(mx)]
for i in range(1, mx):
    for j in range(i, mx, i):
        g[j].append(i)


class Solution:
    def minDifference(self, n: int, k: int) -> List[int]:
        def dfs(i: int, x: int, mi: int, mx: int):
            if i == 0:
                nonlocal cur, ans
                d = max(mx, x) - min(mi, x)
                if d < cur:
                    cur = d
                    path[i] = x
                    ans = path[:]
                return
            for y in g[x]:
                path[i] = y
                dfs(i - 1, x // y, min(mi, y), max(mx, y))

        ans = None
        path = [0] * k
        cur = inf
        dfs(k - 1, n, inf, 0)
        return ans
