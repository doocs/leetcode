function sumZero(n: number): number[] {
    const ans = new Array(n).fill(0);
    for (let i = 1, j = 0; i <= n / 2; ++i) {
        ans[j++] = i;
        ans[j++] = -i;
    }
    return ans;
}
