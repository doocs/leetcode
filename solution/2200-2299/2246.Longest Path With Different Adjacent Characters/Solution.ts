function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    let ans = 0;
    const dfs = (i: number): number => {
        let mx = 0;
        for (const j of g[i]) {
            const x = dfs(j) + 1;
            if (s[i] !== s[j]) {
                ans = Math.max(ans, mx + x);
                mx = Math.max(mx, x);
            }
        }
        return mx;
    };
    dfs(0);
    return ans + 1;
}
