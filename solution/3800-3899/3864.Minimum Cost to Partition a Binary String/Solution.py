class Solution:
    def minCost(self, s: str, encCost: int, flatCost: int) -> int:
        def dfs(l: int, r: int) -> int:
            x = pre[r] - pre[l]
            res = (r - l) * x * encCost if x else flatCost
            if (r - l) % 2 == 0:
                m = (l + r) // 2
                res = min(res, dfs(l, m) + dfs(m, r))
            return res

        n = len(s)
        pre = [0] * (n + 1)
        for i, c in enumerate(s, 1):
            pre[i] = pre[i - 1] + int(c)
        return dfs(0, n)
