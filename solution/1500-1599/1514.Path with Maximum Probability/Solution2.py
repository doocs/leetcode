class Solution:
    def maxProbability(
        self,
        n: int,
        edges: List[List[int]],
        succProb: List[float],
        start: int,
        end: int,
    ) -> float:
        g = defaultdict(list)
        for (a, b), s in zip(edges, succProb):
            g[a].append((b, s))
            g[b].append((a, s))
        d = [0] * n
        vis = [False] * n
        d[start] = 1
        q = deque([start])
        vis[start] = True
        while q:
            i = q.popleft()
            vis[i] = False
            for j, s in g[i]:
                if d[j] < d[i] * s:
                    d[j] = d[i] * s
                    if not vis[j]:
                        q.append(j)
                        vis[j] = True
        return d[end]
