class Solution:
    def sellingWood(self, m: int, n: int, prices: List[List[int]]) -> int:
        @cache
        def dfs(h, w):
            ans = d[h].get(w, 0)
            for i in range(1, h // 2 + 1):
                ans = max(ans, dfs(i, w) + dfs(h - i, w))
            for i in range(1, w // 2 + 1):
                ans = max(ans, dfs(h, i) + dfs(h, w - i))
            return ans

        d = defaultdict(dict)
        for h, w, p in prices:
            d[h][w] = p
        return dfs(m, n)
