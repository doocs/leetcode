function stoneGameIII(stoneValue: number[]): string {
    const n = stoneValue.length;
    const f = new Array<number>(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        let ans = Number.MIN_SAFE_INTEGER;
        let s = 0;
        for (let j = i; j < i + 3 && j < n; ++j) {
            s += stoneValue[j];
            ans = Math.max(ans, s - f[j + 1]);
        }
        f[i] = ans;
    }
    const res = f[0];
    if (res === 0) {
        return 'Tie';
    }
    return res > 0 ? 'Alice' : 'Bob';
}
