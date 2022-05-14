class Solution:
    def minReorder(self, n: int, connections: List[List[int]]) -> int:
        def dfs(u):
            vis[u] = True
            ans = 0
            for v in g[u]:
                if not vis[v]:
                    if (u, v) in s:
                        ans += 1
                    ans += dfs(v)
            return ans

        g = defaultdict(list)
        s = set()
        for a, b in connections:
            g[a].append(b)
            g[b].append(a)
            s.add((a, b))
        vis = [False] * n
        return dfs(0)
