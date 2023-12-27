class Node {
    children: (Node | null)[] = Array(26).fill(null);
    v: number = -1;
}

function minimumCost(
    source: string,
    target: string,
    original: string[],
    changed: string[],
    cost: number[],
): number {
    const m = cost.length;
    const n = source.length;
    const g: number[][] = Array.from({ length: m << 1 }, () => Array(m << 1).fill(Infinity));
    const root: Node = new Node();
    let idx: number = 0;
    const f: number[] = Array(n).fill(-1);
    const insert = (w: string): number => {
        let node: Node = root;
        for (const c of w) {
            const i: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (node.children[i] === null) {
                node.children[i] = new Node();
            }
            node = node.children[i] as Node;
        }
        if (node.v < 0) {
            node.v = idx++;
        }
        return node.v;
    };

    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        let res: number = source[i] === target[i] ? dfs(i + 1) : Infinity;
        let p: Node = root;
        let q: Node = root;
        for (let j = i; j < source.length; ++j) {
            p = p.children[source[j].charCodeAt(0) - 'a'.charCodeAt(0)] as Node;
            q = q.children[target[j].charCodeAt(0) - 'a'.charCodeAt(0)] as Node;
            if (p === null || q === null) {
                break;
            }
            if (p.v < 0 || q.v < 0) {
                continue;
            }
            const t: number = g[p.v][q.v];
            res = Math.min(res, t + dfs(j + 1));
        }
        return (f[i] = res);
    };

    for (let i = 0; i < m; ++i) {
        const x: number = insert(original[i]);
        const y: number = insert(changed[i]);
        g[x][y] = Math.min(g[x][y], cost[i]);
    }

    for (let k = 0; k < idx; ++k) {
        for (let i = 0; i < idx; ++i) {
            if (g[i][k] >= Infinity) {
                continue;
            }
            for (let j = 0; j < idx; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }
    const ans: number = dfs(0);
    return ans >= Infinity ? -1 : ans;
}
