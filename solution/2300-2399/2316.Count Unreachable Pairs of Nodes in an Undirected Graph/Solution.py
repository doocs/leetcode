class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i):
            vis[i] = True
            res = 1
            for j in g[i]:
                if not vis[j]:
                    res += dfs(j)
            return res

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        arr = []
        for i in range(n):
            if not vis[i]:
                arr.append(dfs(i))
        ans = t = 0
        for v in arr:
            t += v
            ans += v * (n - t)
        return ans
