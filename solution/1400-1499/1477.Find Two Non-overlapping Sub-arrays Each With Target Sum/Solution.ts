function minSumOfLengths(arr: number[], target: number): number {
    const d = new Map<number, number>();
    d.set(0, 0);
    let s = 0;
    const n = arr.length;
    const f: number[] = Array(n + 1);
    const inf = 1 << 30;
    f[0] = inf;
    let ans = inf;
    for (let i = 1; i <= n; ++i) {
        const v = arr[i - 1];
        s += v;
        f[i] = f[i - 1];
        if (d.has(s - target)) {
            const j = d.get(s - target)!;
            f[i] = Math.min(f[i], i - j);
            ans = Math.min(ans, f[j] + i - j);
        }
        d.set(s, i);
    }
    return ans > n ? -1 : ans;
}
