class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i: int) -> int:
            if vis[i]:
                return 0
            vis[i] = True
            return 1 + sum(dfs(j) for j in g[i])

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = s = 0
        for i in range(n):
            t = dfs(i)
            ans += s * t
            s += t
        return ans
