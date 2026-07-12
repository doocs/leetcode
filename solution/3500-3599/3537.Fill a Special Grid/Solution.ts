function specialGrid(n: number): number[][] {
    const m = 1 << n;
    const ans = Array.from({ length: m }, () => Array(m).fill(0));
    let val = 0;

    const dfs = (x: number, y: number, k: number): void => {
        if (k === 1) {
            ans[x][y] = val++;
            return;
        }

        const h = k >> 1;
        dfs(x, y, h);
        dfs(x + h, y, h);
        dfs(x + h, y - h, h);
        dfs(x, y - h, h);
    };

    dfs(0, m - 1, m);
    return ans;
}
