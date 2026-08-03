function stoneGameIII(stoneValue: number[]): string {
    const n = stoneValue.length;
    const f = new Array<number>(n).fill(Number.MIN_SAFE_INTEGER);

    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }

        if (f[i] !== Number.MIN_SAFE_INTEGER) {
            return f[i];
        }

        let ans = Number.MIN_SAFE_INTEGER;
        let s = 0;

        for (let j = i; j < i + 3 && j < n; j++) {
            s += stoneValue[j];
            ans = Math.max(ans, s - dfs(j + 1));
        }

        f[i] = ans;
        return ans;
    };

    const res = dfs(0);

    if (res === 0) {
        return 'Tie';
    }
    return res > 0 ? 'Alice' : 'Bob';
}
