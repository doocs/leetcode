class Solution:
    def mostProfitablePath(
        self, edges: List[List[int]], bob: int, amount: List[int]
    ) -> int:
        n = len(edges) + 1
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        parent = [-1] * n
        seen = [False] * n
        seen[0] = True
        stk = [0]
        while stk:
            i = stk.pop()
            for j in g[i]:
                if not seen[j]:
                    seen[j] = True
                    parent[j] = i
                    stk.append(j)
        ts = [n] * n
        x, t = bob, 0
        while x != -1:
            ts[x] = t
            x = parent[x]
            t += 1
        ans = -inf
        walk = [(0, -1, 0, 0)]
        while walk:
            i, fa, t, v = walk.pop()
            if t == ts[i]:
                v += amount[i] // 2
            elif t < ts[i]:
                v += amount[i]
            if len(g[i]) == 1 and g[i][0] == fa:
                ans = max(ans, v)
                continue
            for j in g[i]:
                if j != fa:
                    walk.append((j, i, t + 1, v))
        return ans
