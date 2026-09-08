function longestStrChain(words: string[]): number {
    words.sort((a, b) => a.length - b.length);
    const f = new Map<string, number>();
    let ans = 0;
    for (const w of words) {
        let x = 1;
        for (let i = 0; i < w.length; ++i) {
            const pred = w.slice(0, i) + w.slice(i + 1);
            x = Math.max(x, (f.get(pred) || 0) + 1);
        }
        f.set(w, x);
        ans = Math.max(ans, x);
    }
    return ans;
}
