function minCost(s: string, encCost: number, flatCost: number): number {
    const n = s.length;
    const pre: number[] = new Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        pre[i] = pre[i - 1] + Number(s[i - 1]);
    }

    const dfs = (l: number, r: number): number => {
        const x = pre[r] - pre[l];
        let res = x ? (r - l) * x * encCost : flatCost;

        if ((r - l) % 2 === 0) {
            const m = (l + r) >> 1;
            res = Math.min(res, dfs(l, m) + dfs(m, r));
        }

        return res;
    };

    return dfs(0, n);
}
