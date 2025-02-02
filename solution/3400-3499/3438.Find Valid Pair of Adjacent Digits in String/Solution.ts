function findValidPair(s: string): string {
    const cnt: number[] = Array(10).fill(0);
    for (const c of s) {
        ++cnt[+c];
    }
    for (let i = 1; i < s.length; ++i) {
        const x = +s[i - 1];
        const y = +s[i];
        if (x !== y && cnt[x] === x && cnt[y] === y) {
            return `${x}${y}`;
        }
    }
    return '';
}
