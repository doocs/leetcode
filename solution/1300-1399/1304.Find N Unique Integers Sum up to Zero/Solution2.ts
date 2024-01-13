function sumZero(n: number): number[] {
    const ans = new Array(n).fill(0);
    for (let i = 1; i < n; ++i) {
        ans[i] = i;
    }
    ans[0] = -((n * (n - 1)) / 2);
    return ans;
}
