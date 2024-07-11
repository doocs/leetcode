function lenLongestFibSubseq(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const d: Map<number, number> = new Map();
    for (let i = 0; i < n; ++i) {
        d.set(arr[i], i);
        for (let j = 0; j < i; ++j) {
            f[i][j] = 2;
        }
    }
    let ans = 0;
    for (let i = 2; i < n; ++i) {
        for (let j = 1; j < i; ++j) {
            const t = arr[i] - arr[j];
            const k = d.get(t);
            if (k !== undefined && k < j) {
                f[i][j] = Math.max(f[i][j], f[j][k] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}
