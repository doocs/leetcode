class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        for a, b, w in roads:
            g[a].append((b, w))
            g[b].append((a, w))
        ans = inf
        vis = [False] * (n + 1)
        stk = [1]
        while stk:
            a = stk.pop()
            if vis[a]:
                continue
            vis[a] = True
            for b, w in g[a]:
                ans = min(ans, w)
                if not vis[b]:
                    stk.append(b)
        return ans
