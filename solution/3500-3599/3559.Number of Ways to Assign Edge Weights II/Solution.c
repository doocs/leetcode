int* assignEdgeWeights(int** edges, int edgesSize, int* edgesColSize, int** queries,
    int queriesSize, int* queriesColSize, int* returnSize) {
    int n = edgesSize + 1;
    int m = 32 - __builtin_clz(n);
    int* cnt = calloc(n + 1, sizeof(int));
    for (int i = 0; i < edgesSize; ++i) {
        ++cnt[edges[i][0]];
        ++cnt[edges[i][1]];
    }
    int** g = malloc((n + 1) * sizeof(int*));
    for (int i = 1; i <= n; ++i) {
        g[i] = malloc(cnt[i] * sizeof(int));
        cnt[i] = 0;
    }
    for (int i = 0; i < edgesSize; ++i) {
        int u = edges[i][0], v = edges[i][1];
        g[u][cnt[u]++] = v;
        g[v][cnt[v]++] = u;
    }
    int* f = calloc((n + 1) * m, sizeof(int));
    int* p = calloc(n + 1, sizeof(int));
    int* depth = calloc(n + 1, sizeof(int));
    int* que = malloc(n * sizeof(int));
    int head = 0, tail = 0;
    que[tail++] = 1;
    while (head < tail) {
        int i = que[head++];
        f[i * m] = p[i];
        for (int j = 1; j < m; ++j) {
            f[i * m + j] = f[f[i * m + j - 1] * m + j - 1];
        }
        for (int k = 0; k < cnt[i]; ++k) {
            int j = g[i][k];
            if (j != p[i]) {
                p[j] = i;
                depth[j] = depth[i] + 1;
                que[tail++] = j;
            }
        }
    }
    const int mod = 1e9 + 7;
    int* pow2 = malloc(n * sizeof(int));
    pow2[0] = 1;
    for (int i = 1; i < n; ++i) {
        pow2[i] = pow2[i - 1] * 2 % mod;
    }
    int* ans = malloc(queriesSize * sizeof(int));
    for (int t = 0; t < queriesSize; ++t) {
        int u = queries[t][0], v = queries[t][1];
        int x = u, y = v;
        if (depth[x] < depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        for (int j = m - 1; j >= 0; --j) {
            if (depth[x] - depth[y] >= (1 << j)) {
                x = f[x * m + j];
            }
        }
        for (int j = m - 1; j >= 0; --j) {
            if (f[x * m + j] != f[y * m + j]) {
                x = f[x * m + j];
                y = f[y * m + j];
            }
        }
        if (x != y) {
            x = p[x];
        }
        int d = depth[u] + depth[v] - 2 * depth[x];
        ans[t] = d == 0 ? 0 : pow2[d - 1];
    }
    for (int i = 1; i <= n; ++i) {
        free(g[i]);
    }
    free(g);
    free(cnt);
    free(f);
    free(p);
    free(depth);
    free(que);
    free(pow2);
    *returnSize = queriesSize;
    return ans;
}
