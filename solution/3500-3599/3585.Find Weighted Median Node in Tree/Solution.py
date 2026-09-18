class Solution:
    def findMedian(
        self, n: int, edges: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        m = n.bit_length()
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        f = [[0] * m for _ in range(n)]
        p = [0] * n
        depth = [0] * n
        dist = [0] * n
        q = deque([0])
        while q:
            i = q.popleft()
            f[i][0] = p[i]
            for j in range(1, m):
                f[i][j] = f[f[i][j - 1]][j - 1]
            for j, w in g[i]:
                if j != p[i]:
                    p[j] = i
                    depth[j] = depth[i] + 1
                    dist[j] = dist[i] + w
                    q.append(j)
        ans = []
        for u, v in queries:
            if u == v:
                ans.append(u)
                continue
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
            w = dist[u] + dist[v] - 2 * dist[x]
            if 2 * (dist[u] - dist[x]) >= w:
                cur = u
                for j in range(m - 1, -1, -1):
                    k = f[cur][j]
                    if depth[k] >= depth[x] and 2 * (dist[u] - dist[k]) < w:
                        cur = k
                ans.append(p[cur])
            else:
                cur = v
                for j in range(m - 1, -1, -1):
                    k = f[cur][j]
                    if (
                        depth[k] > depth[x]
                        and 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w
                    ):
                        cur = k
                ans.append(cur)
        return ans
