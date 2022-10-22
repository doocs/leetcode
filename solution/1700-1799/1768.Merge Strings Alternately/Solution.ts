function mergeAlternately(word1: string, word2: string): string {
    const res = [];
    const n = Math.max(word1.length, word2.length);
    for (let i = 0; i < n; i++) {
        word1[i] && res.push(word1[i]);
        word2[i] && res.push(word2[i]);
    }
    return res.join('');
}
