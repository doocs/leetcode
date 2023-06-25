function maximumNumberOfStringPairs(words: string[]): number {
    const cnt: Map<string, number> = new Map();
    let ans = 0;
    for (const w of words) {
        ans += cnt.get(w) || 0;
        const s = w.split('').reverse().join('');
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    return ans;
}
