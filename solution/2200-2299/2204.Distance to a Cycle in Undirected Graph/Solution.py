class Solution:
    def distanceToCycle(self, n: int, edges: List[List[int]]) -> List[int]:
        g = defaultdict(set)
        for a, b in edges:
            g[a].add(b)
            g[b].add(a)
        q = deque(i for i in range(n) if len(g[i]) == 1)
        f = [0] * n
        seq = []
        while q:
            i = q.popleft()
            seq.append(i)
            for j in g[i]:
                g[j].remove(i)
                f[i] = j
                if len(g[j]) == 1:
                    q.append(j)
            g[i].clear()
        ans = [0] * n
        for i in seq[::-1]:
            ans[i] = ans[f[i]] + 1
        return ans
