class Solution:
    def rootCount(
        self, edges: List[List[int]], guesses: List[List[int]], k: int
    ) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        gs = Counter((u, v) for u, v in guesses)
        cnt = 0
        stk = [(0, -1)]
        while stk:
            i, fa = stk.pop()
            for j in g[i]:
                if j != fa:
                    cnt += gs[(i, j)]
                    stk.append((j, i))
        ans = 0
        walk = [(0, -1, cnt)]
        while walk:
            i, fa, c = walk.pop()
            ans += c >= k
            for j in g[i]:
                if j != fa:
                    walk.append((j, i, c - gs[(i, j)] + gs[(j, i)]))
        return ans
