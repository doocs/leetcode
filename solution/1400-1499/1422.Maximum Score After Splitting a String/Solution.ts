function maxScore(s: string): number {
    let [l, r] = [0, 0];
    for (const c of s) {
        r += c === '1' ? 1 : 0;
    }
    let ans = 0;
    for (let i = 0; i < s.length - 1; ++i) {
        if (s[i] === '0') {
            ++l;
        } else {
            --r;
        }
        ans = Math.max(ans, l + r);
    }
    return ans;
}
