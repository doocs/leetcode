class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        s = set(restricted)
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        q = deque([0])
        vis = [False] * n
        for v in restricted:
            vis[v] = True
        ans = 0
        while q:
            i = q.popleft()
            ans += 1
            vis[i] = True
            for j in g[i]:
                if not vis[j]:
                    q.append(j)
        return ans
