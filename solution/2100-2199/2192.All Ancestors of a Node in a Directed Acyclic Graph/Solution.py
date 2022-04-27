class Solution:
    def getAncestors(self, n: int, edges: List[List[int]]) -> List[List[int]]:
        g = defaultdict(list)
        for u, v in edges:
            g[v].append(u)
        ans = []
        for i in range(n):
            if not g[i]:
                ans.append([])
                continue
            q = deque([i])
            vis = [False] * n
            vis[i] = True
            t = []
            while q:
                for _ in range(len(q)):
                    v = q.popleft()
                    for u in g[v]:
                        if not vis[u]:
                            vis[u] = True
                            q.append(u)
                            t.append(u)
            ans.append(sorted(t))
        return ans
