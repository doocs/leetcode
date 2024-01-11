class Solution:
    def maximumSubtreeSize(self, edges: List[List[int]], colors: List[int]) -> int:
        def dfs(a: int, fa: int) -> bool:
            ok = True
            for b in g[a]:
                if b != fa:
                    t = dfs(b, a)
                    ok = ok and colors[a] == colors[b] and t
                    size[a] += size[b]
            if ok:
                nonlocal ans
                ans = max(ans, size[a])
            return ok

        n = len(edges) + 1
        g = [[] for _ in range(n)]
        size = [1] * n
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
