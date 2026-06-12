#define MOD 1000000007
#define LOG 17
#define UP(k, v) up[(k) * (n + 1) + (v)]

int* assignEdgeWeights(int** edges, int edgesSize, int* edgesColSize,
    int** queries, int queriesSize, int* queriesColSize,
    int* returnSize) {
    int n = edgesSize + 1;
    int adjSize, i, j, k, u, v, lca, dist, tmp;
    int* deg = calloc(n + 1, sizeof(int));
    int* head;
    int* adj;
    int* depth;
    int* up;
    int* pow2;
    int* stack;
    int top;
    int* ans;

    for (i = 0; i < edgesSize; i++) {
        deg[edges[i][0]]++;
        deg[edges[i][1]]++;
    }

    head = malloc((n + 2) * sizeof(int));
    head[0] = 0;
    for (i = 1; i <= n + 1; i++)
        head[i] = head[i - 1] + deg[i - 1];

    adj = malloc((adjSize = head[n + 1]) * sizeof(int));
    for (i = 0; i <= n; i++)
        deg[i] = head[i];
    for (i = 0; i < edgesSize; i++) {
        u = edges[i][0];
        v = edges[i][1];
        adj[deg[u]++] = v;
        adj[deg[v]++] = u;
    }
    free(deg);

    depth = calloc(n + 2, sizeof(int));
    depth[0] = -1;
    depth[1] = 0;

    up = calloc(LOG * (n + 1), sizeof(int));

    pow2 = malloc((n + 1) * sizeof(int));
    pow2[0] = 1;
    for (i = 1; i <= n; i++)
        pow2[i] = (pow2[i - 1] << 1) % MOD;

    stack = malloc(n * sizeof(int));
    stack[top = 0] = 1;
    top++;
    while (top) {
        u = stack[--top];
        for (j = head[u]; j < head[u + 1]; j++) {
            v = adj[j];
            if (v != UP(0, u)) {
                UP(0, v) = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }
    }
    free(stack);
    free(head);
    free(adj);

    for (k = 1; k < LOG; k++)
        for (v = 1; v <= n; v++)
            if ((tmp = UP(k - 1, v)) != 0)
                UP(k, v) = UP(k - 1, tmp);

    ans = malloc(queriesSize * sizeof(int));
    for (i = 0; i < queriesSize; i++) {
        u = queries[i][0];
        v = queries[i][1];
        if (u == v) {
            ans[i] = 0;
            continue;
        }
        if (depth[u] < depth[v]) {
            tmp = u;
            u = v;
            v = tmp;
        }
        for (k = LOG - 1; k >= 0; k--)
            if (depth[UP(k, u)] >= depth[v])
                u = UP(k, u);
        if (u != v) {
            for (k = LOG - 1; k >= 0; k--)
                if (UP(k, u) != UP(k, v)) {
                    u = UP(k, u);
                    v = UP(k, v);
                }
            lca = UP(0, u);
        } else {
            lca = u;
        }
        dist = depth[queries[i][0]] + depth[queries[i][1]] - 2 * depth[lca];
        ans[i] = pow2[dist - 1];
    }
    free(depth);
    free(up);
    free(pow2);
    *returnSize = queriesSize;
    return ans;
}
