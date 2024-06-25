function wordCount(startWords: string[], targetWords: string[]): number {
    const s = new Set<number>();
    for (const w of startWords) {
        let x = 0;
        for (const c of w) {
            x ^= 1 << (c.charCodeAt(0) - 97);
        }
        s.add(x);
    }
    let ans = 0;
    for (const w of targetWords) {
        let x = 0;
        for (const c of w) {
            x ^= 1 << (c.charCodeAt(0) - 97);
        }
        for (const c of w) {
            if (s.has(x ^ (1 << (c.charCodeAt(0) - 97)))) {
                ++ans;
                break;
            }
        }
    }
    return ans;
}
