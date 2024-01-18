class TreeAncestor {
    private p: number[][];

    constructor(n: number, parent: number[]) {
        const p = new Array(n).fill(0).map(() => new Array(18).fill(-1));
        for (let i = 0; i < n; ++i) {
            p[i][0] = parent[i];
        }
        for (let j = 1; j < 18; ++j) {
            for (let i = 0; i < n; ++i) {
                if (p[i][j - 1] === -1) {
                    continue;
                }
                p[i][j] = p[p[i][j - 1]][j - 1];
            }
        }
        this.p = p;
    }

    getKthAncestor(node: number, k: number): number {
        for (let i = 17; i >= 0; --i) {
            if (((k >> i) & 1) === 1) {
                node = this.p[node][i];
                if (node === -1) {
                    break;
                }
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * var obj = new TreeAncestor(n, parent)
 * var param_1 = obj.getKthAncestor(node,k)
 */
