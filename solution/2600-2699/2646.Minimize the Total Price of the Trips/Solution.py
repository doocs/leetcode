class Solution:
    def minimumTotalPrice(self, n: int, edges: List[List[int]], price: List[int], trips: List[List[int]]) -> int:
        def dfs(i: int, fa: int, k: int) -> bool:
            cnt[i] += 1
            if i == k:
                return True
            ok = any(j != fa and dfs(j, i, k) for j in g[i])
            if not ok:
                cnt[i] -= 1
            return ok

        @cache
        def dfs2(i: int, fa: int = -1, div: int = 2) -> int:
            s = cnt[i] * price[i] // div
            for j in g[i]:
                if j != fa:
                    x = dfs2(j, i, 1)
                    if div == 1:
                        x = min(x, dfs2(j, i, 2))
                    s += x
            return s

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        cnt = Counter()
        for start, end in trips:
            dfs(start, -1, end)
        return min(dfs2(i) for i in range(n))
