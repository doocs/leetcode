class Solution:
    def networkBecomesIdle(self, edges: List[List[int]], patience: List[int]) -> int:
        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        q = deque([0])
        vis = {0}
        ans = step = 0
        while q:
            step += 1
            for _ in range(len(q)):
                u = q.popleft()
                for v in g[u]:
                    if v in vis:
                        continue
                    vis.add(v)
                    q.append(v)
                    d, t = step * 2, patience[v]
                    ans = max(ans, (d - 1) // t * t + d + 1)
        return ans
