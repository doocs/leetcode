class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)
        q = deque()
        vis = set()
        for i in range(n):
            q.append((i, 1 << i))
            vis.add((i, 1 << i))
        ans = 0
        while 1:
            for _ in range(len(q)):
                i, st = q.popleft()
                if st == (1 << n) - 1:
                    return ans
                for j in graph[i]:
                    nst = st | 1 << j
                    if (j, nst) not in vis:
                        vis.add((j, nst))
                        q.append((j, nst))
            ans += 1
