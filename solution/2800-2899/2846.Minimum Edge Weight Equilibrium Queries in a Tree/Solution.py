class Solution:
    def minOperationsQueries(
        self, n: int, edges: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        m = n.bit_length()
        g = [[] for _ in range(n)]
        f = [[0] * m for _ in range(n)]
        p = [0] * n
        cnt = [None] * n
        depth = [0] * n
        for u, v, w in edges:
            g[u].append((v, w - 1))
            g[v].append((u, w - 1))
        cnt[0] = [0] * 26
        q = deque([0])
        while q:
            i = q.popleft()
            f[i][0] = p[i]
            for j in range(1, m):
                f[i][j] = f[f[i][j - 1]][j - 1]
            for j, w in g[i]:
                if j != p[i]:
                    p[j] = i
                    cnt[j] = cnt[i][:]
                    cnt[j][w] += 1
                    depth[j] = depth[i] + 1
                    q.append(j)
        ans = []
        for u, v in queries:
            x, y = u, v
            if depth[x] < depth[y]:
                x, y = y, x
            for j in reversed(range(m)):
                if depth[x] - depth[y] >= (1 << j):
                    x = f[x][j]
            for j in reversed(range(m)):
                if f[x][j] != f[y][j]:
                    x, y = f[x][j], f[y][j]
            if x != y:
                x = p[x]
            mx = max(cnt[u][j] + cnt[v][j] - 2 * cnt[x][j] for j in range(26))
            ans.append(depth[u] + depth[v] - 2 * depth[x] - mx)
        return ans
