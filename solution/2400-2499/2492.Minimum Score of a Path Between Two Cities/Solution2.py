class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        g = defaultdict(list)
        for a, b, d in roads:
            g[a].append((b, d))
            g[b].append((a, d))
        vis = [False] * (n + 1)
        vis[1] = True
        ans = inf
        q = deque([1])
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                for j, d in g[i]:
                    ans = min(ans, d)
                    if not vis[j]:
                        vis[j] = True
                        q.append(j)
        return ans
