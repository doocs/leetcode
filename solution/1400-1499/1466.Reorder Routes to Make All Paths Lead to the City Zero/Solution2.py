class Solution:
    def minReorder(self, n: int, connections: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in connections:
            g[a].append((b, 1))
            g[b].append((a, 0))
        q = deque([0])
        vis = {0}
        ans = 0
        while q:
            a = q.popleft()
            for b, c in g[a]:
                if b not in vis:
                    vis.add(b)
                    q.append(b)
                    ans += c
        return ans
