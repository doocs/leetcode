class Solution:
    def minCost(self, n: int, edges: List[List[int]], k: int) -> int:
        def check(idx: int) -> bool:
            g = [[] for _ in range(n)]
            for u, v, _ in edges[: idx + 1]:
                g[u].append(v)
                g[v].append(u)
            q = [0]
            dist = 0
            vis = [False] * n
            vis[0] = True
            while q:
                nq = []
                for u in q:
                    if u == n - 1:
                        return dist <= k
                    for v in g[u]:
                        if not vis[v]:
                            vis[v] = True
                            nq.append(v)
                q = nq
                dist += 1
            return False

        m = len(edges)
        edges.sort(key=lambda x: x[2])
        l, r = 0, m - 1
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return edges[l][2] if check(l) else -1
