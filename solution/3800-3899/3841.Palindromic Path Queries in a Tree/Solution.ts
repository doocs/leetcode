class BinaryIndexedTree {
    n: number;
    tree: number[];

    constructor(n: number) {
        this.n = n;
        this.tree = Array(n + 1).fill(0);
    }

    update(i: number, delta: number): void {
        for (i++; i <= this.n; i += i & -i) {
            this.tree[i] ^= delta;
        }
    }

    query(i: number): number {
        let res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= this.tree[i];
        }
        return res;
    }
}

function palindromePath(n: number, edges: number[][], s: string, queries: string[]): boolean[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const parent = Array(n).fill(0);
    const depth = Array(n).fill(0);
    const tin = Array(n).fill(0);
    const tout = Array(n).fill(0);
    let timer = 0;

    function dfs(u: number, p: number): void {
        parent[u] = p === -1 ? 0 : p;
        tin[u] = timer++;
        for (const v of g[u]) {
            if (v !== p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
        tout[u] = timer;
    }
    dfs(0, -1);

    const m = 32 - Math.clz32(n);
    const up: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    up[0] = [...parent];
    for (let j = 1; j < m; j++) {
        for (let i = 0; i < n; i++) {
            up[j][i] = up[j - 1][up[j - 1][i]];
        }
    }

    const bit = Array(n).fill(0);
    for (let i = 0; i < n; i++) {
        bit[i] = 1 << (s.charCodeAt(i) - 97);
    }

    const tree = new BinaryIndexedTree(n);
    for (let i = 0; i < n; i++) {
        tree.update(tin[i], bit[i]);
        tree.update(tout[i], bit[i]);
    }

    function lca(u: number, v: number): number {
        if (depth[u] < depth[v]) {
            [u, v] = [v, u];
        }
        let d = depth[u] - depth[v];
        for (let j = 0; d > 0; j++, d >>= 1) {
            if (d & 1) {
                u = up[j][u];
            }
        }
        if (u === v) {
            return u;
        }
        for (let j = m - 1; j >= 0; j--) {
            if (up[j][u] !== up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }

    const ans: boolean[] = [];
    for (const q of queries) {
        const parts = q.split(' ');
        const u = Number(parts[1]);
        if (parts[0] === 'update') {
            const y = 1 << (parts[2].charCodeAt(0) - 97);
            if (bit[u] !== y) {
                const delta = bit[u] ^ y;
                tree.update(tin[u], delta);
                tree.update(tout[u], delta);
                bit[u] = y;
            }
        } else {
            const v = Number(parts[2]);
            const w = lca(u, v);
            const mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
            ans.push((mask & (mask - 1)) === 0);
        }
    }
    return ans;
}
