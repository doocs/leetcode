function maxScore(parent: number[], op: number[], score: number[]): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parent[i]].push(i);
    }
    const dfs = (i: number): number => {
        if (g[i].length === 0) {
            return score[i];
        }
        let tot = score[i];
        let mn = Infinity;
        for (const j of g[i]) {
            const t = dfs(j);
            mn = Math.min(mn, t);
            tot += t;
        }
        if (op[i] === 0) {
            return tot - Math.min(0, mn);
        }
        return tot - mn;
    };
    return dfs(0);
}
