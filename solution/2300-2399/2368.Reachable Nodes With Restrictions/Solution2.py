class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set(restricted + [0])
        q = deque([0])
        ans = 0
        while q:
            i = q.popleft()
            ans += 1
            for j in g[i]:
                if j not in vis:
                    q.append(j)
                    vis.add(j)
        return ans
