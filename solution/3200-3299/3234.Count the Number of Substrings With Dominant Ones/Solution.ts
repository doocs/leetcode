function numberOfSubstrings(s: string): number {
    const n = s.length;
    const nxt: number[] = Array(n + 1).fill(0);
    nxt[n] = n;
    for (let i = n - 1; i >= 0; --i) {
        nxt[i] = nxt[i + 1];
        if (s[i] === '0') {
            nxt[i] = i;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let cnt0 = s[i] === '0' ? 1 : 0;
        let j = i;
        while (j < n && cnt0 * cnt0 <= n) {
            const cnt1 = nxt[j + 1] - i - cnt0;
            if (cnt1 >= cnt0 * cnt0) {
                ans += Math.min(nxt[j + 1] - j, cnt1 - cnt0 * cnt0 + 1);
            }
            j = nxt[j + 1];
            ++cnt0;
        }
    }
    return ans;
}
