function minFlipsMonoIncr(s: string): number {
    const n = s.length;
    let [left0, right0] = [0, 0];
    for (const c of s) {
        right0 += c === '0' ? 1 : 0;
    }
    let ans = Math.min(right0, n - right0);
    for (let i = 1; i <= n; ++i) {
        const x = s[i - 1] === '0' ? 0 : 1;
        right0 -= x ^ 1;
        left0 += x ^ 1;
        ans = Math.min(ans, i - left0 + right0);
    }
    return ans;
}
