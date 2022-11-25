class Solution:
    def reachableNodes(self, edges: List[List[int]], maxMoves: int, n: int) -> int:
        g = defaultdict(list)
        for u, v, cnt in edges:
            g[u].append((v, cnt + 1))
            g[v].append((u, cnt + 1))
        q = [(0, 0)]
        dist = [0] + [inf] * n
        while q:
            d, u = heappop(q)
            for v, cnt in g[u]:
                if (t := d + cnt) < dist[v]:
                    dist[v] = t
                    q.append((t, v))
        ans = sum(d <= maxMoves for d in dist)
        for u, v, cnt in edges:
            a = min(cnt, max(0, maxMoves - dist[u]))
            b = min(cnt, max(0, maxMoves - dist[v]))
            ans += min(cnt, a + b)
        return ans
