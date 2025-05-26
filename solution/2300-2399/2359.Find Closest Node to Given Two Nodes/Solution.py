class Solution:
    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        def f(i):
            dist = [inf] * n
            dist[i] = 0
            q = deque([i])
            while q:
                i = q.popleft()
                for j in g[i]:
                    if dist[j] == inf:
                        dist[j] = dist[i] + 1
                        q.append(j)
            return dist

        g = defaultdict(list)
        for i, j in enumerate(edges):
            if j != -1:
                g[i].append(j)
        n = len(edges)
        d1 = f(node1)
        d2 = f(node2)
        ans, d = -1, inf
        for i, (a, b) in enumerate(zip(d1, d2)):
            if (t := max(a, b)) < d:
                d = t
                ans = i
        return ans
