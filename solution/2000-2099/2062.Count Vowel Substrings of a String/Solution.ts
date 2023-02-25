function countVowelSubstrings(word: string): number {
    let ans = 0;
    const n = word.length;
    for (let i = 0; i < n; ++i) {
        const t = new Set<string>();
        for (let j = i; j < n; ++j) {
            const c = word[j];
            if (
                !(c === 'a' || c === 'e' || c === 'i' || c === 'o' || c === 'u')
            ) {
                break;
            }
            t.add(c);
            if (t.size === 5) {
                ans++;
            }
        }
    }
    return ans;
}
