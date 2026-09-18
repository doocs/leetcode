function findMedian(n: number, edges: number[][], queries: number[][]): number[] {
    const m = 32 - Math.clz32(n);
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const f: number[][] = Array.from({ length: n }, () => Array(m).fill(0));
    const p: number[] = Array(n).fill(0);
    const depth: number[] = Array(n).fill(0);
    const dist: number[] = Array(n).fill(0);
    const q: number[] = [0];
    for (let qq = 0; qq < q.length; ++qq) {
        const i = q[qq];
        f[i][0] = p[i];
        for (let j = 1; j < m; ++j) {
            f[i][j] = f[f[i][j - 1]][j - 1];
        }
        for (const [j, w] of g[i]) {
            if (j !== p[i]) {
                p[j] = i;
                depth[j] = depth[i] + 1;
                dist[j] = dist[i] + w;
                q.push(j);
            }
        }
    }
    const ans: number[] = [];
    for (const [u, v] of queries) {
        if (u === v) {
            ans.push(u);
            continue;
        }
        let x = u,
            y = v;
        if (depth[x] < depth[y]) {
            [x, y] = [y, x];
        }
        for (let j = m - 1; j >= 0; --j) {
            if (depth[x] - depth[y] >= 1 << j) {
                x = f[x][j];
            }
        }
        for (let j = m - 1; j >= 0; --j) {
            if (f[x][j] !== f[y][j]) {
                x = f[x][j];
                y = f[y][j];
            }
        }
        if (x !== y) {
            x = p[x];
        }
        const w = dist[u] + dist[v] - 2 * dist[x];
        if (2 * (dist[u] - dist[x]) >= w) {
            let cur = u;
            for (let j = m - 1; j >= 0; --j) {
                const k = f[cur][j];
                if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                    cur = k;
                }
            }
            ans.push(p[cur]);
        } else {
            let cur = v;
            for (let j = m - 1; j >= 0; --j) {
                const k = f[cur][j];
                if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                    cur = k;
                }
            }
            ans.push(cur);
        }
    }
    return ans;
}
