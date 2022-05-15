class Solution:
    def frogPosition(
        self, n: int, edges: List[List[int]], t: int, target: int
    ) -> float:
        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        q = deque([(1, 1.0)])
        vis = [False] * (n + 1)
        vis[1] = True
        while q and t >= 0:
            for _ in range(len(q)):
                u, p = q.popleft()
                nxt = [v for v in g[u] if not vis[v]]
                if u == target and (not nxt or t == 0):
                    return p
                for v in nxt:
                    vis[v] = True
                    q.append((v, p / len(nxt)))
            t -= 1
        return 0
