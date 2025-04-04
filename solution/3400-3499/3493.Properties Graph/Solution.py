class Solution:
    def numberOfComponents(self, properties: List[List[int]], k: int) -> int:
        def dfs(i: int) -> None:
            vis[i] = True
            for j in g[i]:
                if not vis[j]:
                    dfs(j)

        n = len(properties)
        ss = list(map(set, properties))
        g = [[] for _ in range(n)]
        for i, s1 in enumerate(ss):
            for j in range(i):
                s2 = ss[j]
                if len(s1 & s2) >= k:
                    g[i].append(j)
                    g[j].append(i)
        ans = 0
        vis = [False] * n
        for i in range(n):
            if not vis[i]:
                dfs(i)
                ans += 1
        return ans
