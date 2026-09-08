function longestStrChain(words: string[]): number {
    const check = (a: string, b: string): boolean => {
        if (a.length + 1 !== b.length) {
            return false;
        }
        let i = 0;
        for (const c of b) {
            if (i < a.length && a[i] === c) {
                ++i;
            }
        }
        return i === a.length;
    };

    words.sort((a, b) => a.length - b.length);
    const n = words.length;
    const f: number[] = Array(n).fill(1);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (check(words[j], words[i])) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
    }
    return Math.max(...f);
}
