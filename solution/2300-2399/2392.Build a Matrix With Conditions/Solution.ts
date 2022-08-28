function buildMatrix(
    k: number,
    rowConditions: number[][],
    colConditions: number[][],
): number[][] {
    function f(cond) {
        const g = Array.from({ length: k + 1 }, () => []);
        const indeg = new Array(k + 1).fill(0);
        for (const [a, b] of cond) {
            g[a].push(b);
            ++indeg[b];
        }
        const q = [];
        for (let i = 1; i < indeg.length; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        const res = [];
        while (q.length) {
            for (let n = q.length; n; --n) {
                const i = q.shift();
                res.push(i);
                for (const j of g[i]) {
                    if (--indeg[j] == 0) {
                        q.push(j);
                    }
                }
            }
        }
        return res.length == k ? res : [];
    }

    const row = f(rowConditions);
    const col = f(colConditions);
    if (!row.length || !col.length) return [];
    const ans = Array.from({ length: k }, () => new Array(k).fill(0));
    const m = new Array(k + 1).fill(0);
    for (let i = 0; i < k; ++i) {
        m[col[i]] = i;
    }
    for (let i = 0; i < k; ++i) {
        ans[i][m[row[i]]] = row[i];
    }
    return ans;
}
