function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    const f: number[] = Array(n).fill(arr[0]);
    const g: number[] = Array(n).fill(0);
    let ans = f[0];
    for (let i = 1; i < n; ++i) {
        f[i] = g[i - 1] + arr[i] * ((i >> 1) + 1);
        g[i] = f[i - 1] + arr[i] * ((i + 1) >> 1);
        ans += f[i];
    }
    return ans;
}
