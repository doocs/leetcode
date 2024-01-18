function mergeAlternately(word1: string, word2: string): string {
    const ans: string[] = [];
    const [m, n] = [word1.length, word2.length];
    for (let i = 0; i < m || i < n; ++i) {
        if (i < m) {
            ans.push(word1[i]);
        }
        if (i < n) {
            ans.push(word2[i]);
        }
    }
    return ans.join('');
}
