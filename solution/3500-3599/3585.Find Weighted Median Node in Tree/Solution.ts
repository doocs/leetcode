function findMedian(n: number, edges: number[][], queries: number[][]): number[] {
    const LOG = 17;
    const adj: number[][][] = Array.from({length: n}, () => []);
    for (const [u, v, w] of edges) {
        adj[u].push([v, w]);
        adj[v].push([u, w]);
    }

    const depth = new Array<number>(n).fill(0);
    const dist = new Array<number>(n).fill(0);
    const up: number[][] = Array.from({length: LOG}, () => new Array<number>(n).fill(-1));

    // BFS from root 0 to compute depth, dist, and parent
    const visited = new Uint8Array(n);
    const queue: number[] = [0];
    visited[0] = 1;
    let head = 0;
    while (head < queue.length) {
        const u = queue[head++];
        for (const [v, w] of adj[u]) {
            if (!visited[v]) {
                visited[v] = 1;
                depth[v] = depth[u] + 1;
                dist[v] = dist[u] + w;
                up[0][v] = u;
                queue.push(v);
            }
        }
    }

    // Build binary lifting table
    for (let k = 1; k < LOG; k++) {
        for (let v = 0; v < n; v++) {
            if (up[k - 1][v] !== -1) up[k][v] = up[k - 1][up[k - 1][v]];
        }
    }

    function lca(a: number, b: number): number {
        if (depth[a] < depth[b]) [a, b] = [b, a];
        let diff = depth[a] - depth[b];
        for (let k = 0; diff > 0; k++, diff >>= 1) {
            if (diff & 1) a = up[k][a];
        }
        if (a === b) return a;
        for (let k = LOG - 1; k >= 0; k--) {
            if (up[k][a] !== up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }
        return up[0][a];
    }

    const ans: number[] = [];

    for (const [u, v] of queries) {
        if (u === v) { ans.push(u); continue; }

        const l = lca(u, v);
        const W = dist[u] + dist[v] - 2 * dist[l];

        // Case 1: median is on u → LCA segment
        if (2 * (dist[u] - dist[l]) >= W) {
            let cur = u;
            for (let k = LOG - 1; k >= 0; k--) {
                const j = up[k][cur];
                if (j !== -1 && depth[j] >= depth[l] && 2 * (dist[u] - dist[j]) < W) {
                    cur = j;
                }
            }
            ans.push(up[0][cur]); // one step further = first node with prefix ≥ W/2
        }
        // Case 2: median is on LCA → v segment (below LCA)
        else {
            let cur = v;
            for (let k = LOG - 1; k >= 0; k--) {
                const j = up[k][cur];
                if (j !== -1 && depth[j] > depth[l] && 2 * (dist[u] + dist[j] - 2 * dist[l]) >= W) {
                    cur = j;
                }
            }
            ans.push(cur); // highest node on v-side where prefix ≥ W/2
        }
    }

    return ans;
}