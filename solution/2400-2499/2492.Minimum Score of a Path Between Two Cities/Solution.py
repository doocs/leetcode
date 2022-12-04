class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        def dfs(i):
            nonlocal ans
            for j, d in g[i]:
                ans = min(ans, d)
                if not vis[j]:
                    vis[j] = True
                    dfs(j)

        g = defaultdict(list)
        for a, b, d in roads:
            g[a].append((b, d))
            g[b].append((a, d))
        vis = [False] * (n + 1)
        ans = inf
        dfs(1)
        return ans
