class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        for a, b, w in roads:
            g[a].append((b, w))
            g[b].append((a, w))
        vis = [False] * (n + 1)
        vis[1] = True
        ans = inf
        q = deque([1])
        while q:
            for _ in range(len(q)):
                a = q.popleft()
                for b, w in g[a]:
                    ans = min(ans, w)
                    if not vis[b]:
                        vis[b] = True
                        q.append(b)
        return ans
