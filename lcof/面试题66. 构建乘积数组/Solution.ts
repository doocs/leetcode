function constructArr(a: number[]): number[] {
    const n = a.length;
    const ans: number[] = Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        ans[i] = left;
        left *= a[i];
    }
    for (let i = n - 1, right = 1; i >= 0; --i) {
        ans[i] *= right;
        right *= a[i];
    }
    return ans;
}
