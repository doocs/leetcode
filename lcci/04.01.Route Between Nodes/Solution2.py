class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        g = defaultdict(list)
        for u, v in graph:
            g[u].append(v)
        q = deque([start])
        vis = {start}
        while q:
            u = q.popleft()
            if u == target:
                return True
            for v in g[u]:
                if v not in vis:
                    vis.add(v)
                    q.append(v)
        return False
