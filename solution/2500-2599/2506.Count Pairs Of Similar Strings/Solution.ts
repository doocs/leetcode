function similarPairs(words: string[]): number {
    let ans = 0;
    const cnt: Map<number, number> = new Map();
    for (const w of words) {
        let v = 0;
        for (let i = 0; i < w.length; ++i) {
            v |= 1 << (w.charCodeAt(i) - 'a'.charCodeAt(0));
        }
        ans += cnt.get(v) || 0;
        cnt.set(v, (cnt.get(v) || 0) + 1);
    }
    return ans;
}
