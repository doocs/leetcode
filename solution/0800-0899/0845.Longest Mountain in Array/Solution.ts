function longestMountain(arr: number[]): number {
    const n = arr.length;
    const f: number[] = Array(n).fill(1);
    const g: number[] = Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        if (arr[i] > arr[i - 1]) {
            f[i] = f[i - 1] + 1;
        }
    }
    let ans = 0;
    for (let i = n - 2; i >= 0; --i) {
        if (arr[i] > arr[i + 1]) {
            g[i] = g[i + 1] + 1;
            if (f[i] > 1) {
                ans = Math.max(ans, f[i] + g[i] - 1);
            }
        }
    }
    return ans;
}
