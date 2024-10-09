function maxOperations(s: string): number {
    let [ans, cnt] = [0, 0];
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] === '1') {
            ++cnt;
        } else if (i && s[i - 1] === '1') {
            ans += cnt;
        }
    }
    return ans;
}
