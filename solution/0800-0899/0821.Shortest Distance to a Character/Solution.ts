function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    const inf = 1 << 30;
    const ans: number[] = new Array(n).fill(inf);
    for (let i = 0, pre = -inf; i < n; ++i) {
        if (s[i] === c) {
            pre = i;
        }
        ans[i] = i - pre;
    }
    for (let i = n - 1, suf = inf; i >= 0; --i) {
        if (s[i] === c) {
            suf = i;
        }
        ans[i] = Math.min(ans[i], suf - i);
    }
    return ans;
}
