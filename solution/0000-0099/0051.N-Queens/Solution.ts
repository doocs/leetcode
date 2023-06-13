function solveNQueens(n: number): string[][] {
    const col: number[] = new Array(n).fill(0);
    const dg: number[] = new Array(n << 1).fill(0);
    const udg: number[] = new Array(n << 1).fill(0);
    const ans: string[][] = [];
    const t: string[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill('.'));
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.map(x => x.join('')));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] === 0) {
                t[i][j] = 'Q';
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                t[i][j] = '.';
            }
        }
    };
    dfs(0);
    return ans;
}
