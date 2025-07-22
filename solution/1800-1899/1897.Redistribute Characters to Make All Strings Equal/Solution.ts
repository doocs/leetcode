function makeEqual(words: string[]): boolean {
    const cnt: Record<string, number> = {};
    for (const w of words) {
        for (const c of w) {
            cnt[c] = (cnt[c] || 0) + 1;
        }
    }
    const n = words.length;
    return Object.values(cnt).every(v => v % n === 0);
}
