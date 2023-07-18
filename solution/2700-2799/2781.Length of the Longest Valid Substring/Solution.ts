function longestValidSubstring(word: string, forbidden: string[]): number {
    const s: Set<string> = new Set(forbidden);
    const n = word.length;
    let ans = 0;
    for (let i = 0, j = 0; j < n; ++j) {
        for (let k = j; k > Math.max(j - 10, i - 1); --k) {
            if (s.has(word.substring(k, j + 1))) {
                i = k + 1;
                break;
            }
        }
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
}
