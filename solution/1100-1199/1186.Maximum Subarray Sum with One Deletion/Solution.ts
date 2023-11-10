function maximumSum(arr: number[]): number {
    const n = arr.length;
    const left: number[] = Array(n).fill(0);
    const right: number[] = Array(n).fill(0);
    for (let i = 0, s = 0; i < n; ++i) {
        s = Math.max(s, 0) + arr[i];
        left[i] = s;
    }
    for (let i = n - 1, s = 0; i >= 0; --i) {
        s = Math.max(s, 0) + arr[i];
        right[i] = s;
    }
    let ans = Math.max(...left);
    for (let i = 1; i < n - 1; ++i) {
        ans = Math.max(ans, left[i - 1] + right[i + 1]);
    }
    return ans;
}
