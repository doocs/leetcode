function minFlips(s: string): number {
    const n = s.length;
    const target = '01';
    let cnt = 0;
    for (let i = 0; i < n; ++i) {
        if (s[i] !== target[i & 1]) {
            ++cnt;
        }
    }
    let ans = Math.min(cnt, n - cnt);
    for (let i = 0; i < n; ++i) {
        if (s[i] !== target[i & 1]) {
            --cnt;
        }
        if (s[i] !== target[(i + n) & 1]) {
            ++cnt;
        }
        ans = Math.min(ans, cnt, n - cnt);
    }
    return ans;
}
