function combine(n: number, k: number): number[][] {
    const res: number[][] = [];
    const dfs = (i: number, t: number[]) => {
        if (t.length == k) {
            res.push(t);
            return;
        }
        // 剪枝
        if (t.length + n - i + 1 < k) {
            return;
        }
        for (let j = i; j <= n; j++) {
            dfs(j + 1, [...t, j]);
        }
    };
    dfs(1, []);
    return res;
}
