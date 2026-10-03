class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = 0
        for i in range(n):
            if vis[i]:
                continue
            ans += 1
            stk = [i]
            vis[i] = True
            while stk:
                u = stk.pop()
                for v in g[u]:
                    if not vis[v]:
                        vis[v] = True
                        stk.append(v)
        return ans
