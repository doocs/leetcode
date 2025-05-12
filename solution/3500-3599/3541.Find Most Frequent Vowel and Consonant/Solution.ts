function maxFreqSum(s: string): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let [a, b] = [0, 0];
    for (let i = 0; i < 26; ++i) {
        const c = String.fromCharCode(i + 97);
        if ('aeiou'.includes(c)) {
            a = Math.max(a, cnt[i]);
        } else {
            b = Math.max(b, cnt[i]);
        }
    }
    return a + b;
}
