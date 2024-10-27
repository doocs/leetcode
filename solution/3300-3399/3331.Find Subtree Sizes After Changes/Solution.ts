function findSubtreeSizes(parent: number[], s: string): number[] {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const d: number[][] = Array.from({ length: 26 }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    const ans: number[] = Array(n).fill(1);
    const dfs = (i: number, fa: number): void => {
        const idx = s.charCodeAt(i) - 97;
        d[idx].push(i);
        for (const j of g[i]) {
            dfs(j, i);
        }
        const k = d[idx].length > 1 ? d[idx].at(-2)! : fa;
        if (k >= 0) {
            ans[k] += ans[i];
        }
        d[idx].pop();
    };
    dfs(0, -1);
    return ans;
}
