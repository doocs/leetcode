class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        g = [[] for _ in range(n)]
        for a, b in graph:
            g[a].append(b)
        vis = {start}
        q = deque([start])
        while q:
            i = q.popleft()
            if i == target:
                return True
            for j in g[i]:
                if j not in vis:
                    vis.add(j)
                    q.append(j)
        return False
