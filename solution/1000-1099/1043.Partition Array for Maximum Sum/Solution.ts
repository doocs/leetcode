function maxSumAfterPartitioning(arr: number[], k: number): number {
    const n: number = arr.length;
    const f: number[] = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        let mx: number = 0;
        for (let j = i; j > Math.max(0, i - k); --j) {
            mx = Math.max(mx, arr[j - 1]);
            f[i] = Math.max(f[i], f[j - 1] + mx * (i - j + 1));
        }
    }
    return f[n];
}
