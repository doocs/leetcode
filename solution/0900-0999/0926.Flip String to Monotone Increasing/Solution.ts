function minFlipsMonoIncr(s: string): number {
    let tot = 0;
    for (const c of s) {
        tot += c === '0' ? 1 : 0;
    }
    let [ans, cur] = [tot, 0];
    for (let i = 1; i <= s.length; ++i) {
        cur += s[i - 1] === '0' ? 1 : 0;
        ans = Math.min(ans, i - cur + tot - cur);
    }
    return ans;
}
