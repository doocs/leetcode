function largestInteger(n: number, s: number): number {
    if (n * 9 < s) {
        return -1;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const x = Math.min(s, 9);
        ans = ans * 10 + x;
        s -= x;
    }
    return ans;
}
