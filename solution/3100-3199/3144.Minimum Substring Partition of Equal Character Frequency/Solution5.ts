function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const cnt: number[] = Array(26).fill(0);
        let ans = n - i;
        let [k, m] = [0, 0];
        for (let j = i; j < n; ++j) {
            const x = s.charCodeAt(j) - 97;
            k += ++cnt[x] === 1 ? 1 : 0;
            m = Math.max(m, cnt[x]);
            if (j - i + 1 === k * m) {
                ans = Math.min(ans, 1 + f[j + 1]);
            }
        }
        f[i] = ans;
    }
    return f[0];
}
