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
    const f: number[] = Array(n + 1).fill(Infinity);
    f[n] = 0;
    for (let i = n - 1; i >= 0; --i) {
        let res: number = source[i] === target[i] ? f[i + 1] : Infinity;
        let p: Node | null = root;
        let q: Node | null = root;
        for (let j = i; j < n; ++j) {
            p = p.children[source.charCodeAt(j) - 97];
            q = q.children[target.charCodeAt(j) - 97];
            if (p === null || q === null) {
                break;
            }
            if (p.v < 0 || q.v < 0) {
                continue;
            }
            res = Math.min(res, g[p.v][q.v] + f[j + 1]);
        }
        f[i] = res;
    }
    return f[0] >= Infinity ? -1 : f[0];
}
