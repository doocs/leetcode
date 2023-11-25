function minimumSteps(s: string): number {
    const n = s.length;
    let [ans, cnt] = [0, 0];
    for (let i = n - 1; ~i; --i) {
        if (s[i] === '1') {
            ++cnt;
            ans += n - i - cnt;
        }
    }
    return ans;
}
