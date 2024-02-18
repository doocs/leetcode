function countPrefixSuffixPairs(words: string[]): number {
    let ans = 0;
    for (let i = 0; i < words.length; ++i) {
        const s = words[i];
        for (const t of words.slice(i + 1)) {
            if (t.startsWith(s) && t.endsWith(s)) {
                ++ans;
            }
        }
    }
    return ans;
}
