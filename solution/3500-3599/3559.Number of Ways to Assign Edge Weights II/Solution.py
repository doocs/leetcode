class Solution:
    def assignEdgeWeights(
        self, edges: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        n = len(edges) + 1
        m = n.bit_length()
        g = [[] for _ in range(n + 1)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        f = [[0] * m for _ in range(n + 1)]
        p = [0] * (n + 1)
        depth = [0] * (n + 1)
        q = deque([1])
        while q:
            i = q.popleft()
            f[i][0] = p[i]
            for j in range(1, m):
                f[i][j] = f[f[i][j - 1]][j - 1]
            for j in g[i]:
                if j != p[i]:
                    p[j] = i
                    depth[j] = depth[i] + 1
                    q.append(j)
        mod = 10**9 + 7
        pow2 = [1] * n
        for i in range(1, n):
            pow2[i] = pow2[i - 1] * 2 % mod
        ans = []
        for u, v in queries:
            x, y = u, v
            if depth[x] < depth[y]:
                x, y = y, x
            for j in range(m - 1, -1, -1):
                if depth[x] - depth[y] >= (1 << j):
                    x = f[x][j]
            for j in range(m - 1, -1, -1):
                if f[x][j] != f[y][j]:
                    x, y = f[x][j], f[y][j]
            if x != y:
                x = p[x]
            d = depth[u] + depth[v] - 2 * depth[x]
            ans.append(0 if d == 0 else pow2[d - 1])
        return ans
