function totalNQueens(n: number): number {
    const cols: boolean[] = Array(10).fill(false);
    const dg: boolean[] = Array(20).fill(false);
    const udg: boolean[] = Array(20).fill(false);
    let ans = 0;
    const dfs = (i: number) => {
        if (i === n) {
            ++ans;
            return;
        }
        for (let j = 0; j < n; ++j) {
            let [a, b] = [i + j, i - j + n];
            if (cols[j] || dg[a] || udg[b]) {
                continue;
            }
            cols[j] = dg[a] = udg[b] = true;
            dfs(i + 1);
            cols[j] = dg[a] = udg[b] = false;
        }
    };
    dfs(0);
    return ans;
}
