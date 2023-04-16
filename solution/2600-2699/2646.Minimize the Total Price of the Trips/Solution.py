class Solution:
    def minimumTotalPrice(
        self, n: int, edges: List[List[int]], price: List[int], trips: List[List[int]]
    ) -> int:
        def dfs(i: int, fa: int, k: int) -> bool:
            cnt[i] += 1
            if i == k:
                return True
            ok = any(j != fa and dfs(j, i, k) for j in g[i])
            if not ok:
                cnt[i] -= 1
            return ok

        def dfs2(i: int, fa: int) -> (int, int):
            a = cnt[i] * price[i]
            b = a // 2
            for j in g[i]:
                if j != fa:
                    x, y = dfs2(j, i)
                    a += min(x, y)
                    b += x
            return a, b

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        cnt = Counter()
        for start, end in trips:
            dfs(start, -1, end)
        return min(dfs2(0, -1))
