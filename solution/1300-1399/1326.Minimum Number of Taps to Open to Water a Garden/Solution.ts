function minTaps(n: number, ranges: number[]): number {
    const last = new Array(n + 1).fill(0);
    for (let i = 0; i < n + 1; ++i) {
        const l = Math.max(0, i - ranges[i]);
        const r = i + ranges[i];
        last[l] = Math.max(last[l], r);
    }
    let ans = 0;
    let mx = 0;
    let pre = 0;
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, last[i]);
        if (mx <= i) {
            return -1;
        }
        if (pre == i) {
            ++ans;
            pre = mx;
        }
    }
    return ans;
}
