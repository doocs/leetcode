function minimumKeypresses(s: string): number {
    const cnt: number[] = Array(26).fill(0);
    const a = 'a'.charCodeAt(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - a];
    }
    cnt.sort((a, b) => b - a);
    let [ans, k] = [0, 1];
    for (let i = 1; i <= 26; ++i) {
        ans += k * cnt[i - 1];
        if (i % 9 === 0) {
            ++k;
        }
    }
    return ans;
}
