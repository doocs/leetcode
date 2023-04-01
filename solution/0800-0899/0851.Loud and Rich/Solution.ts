function loudAndRich(richer: number[][], quiet: number[]): number[] {
    const n = quiet.length;
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [a, b] of richer) {
        g[b].push(a);
    }
    const ans: number[] = new Array(n).fill(-1);
    const dfs = (i: number) => {
        if (ans[i] != -1) {
            return ans;
        }
        ans[i] = i;
        for (const j of g[i]) {
            dfs(j);
            if (quiet[ans[j]] < quiet[ans[i]]) {
                ans[i] = ans[j];
            }
        }
    };
    for (let i = 0; i < n; ++i) {
        dfs(i);
    }
    return ans;
}
