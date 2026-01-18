function vowelConsonantScore(s: string): number {
    let [v, c] = [0, 0];
    for (const ch of s) {
        if (/[a-zA-Z]/.test(ch)) {
            c++;
            if ('aeiou'.includes(ch)) {
                v++;
            }
        }
    }
    c -= v;
    return c === 0 ? 0 : Math.floor(v / c);
}
