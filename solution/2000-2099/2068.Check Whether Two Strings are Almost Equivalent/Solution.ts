function checkAlmostEquivalent(word1: string, word2: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of word1) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    for (const c of word2) {
        --cnt[c.charCodeAt(0) - 97];
    }
    return cnt.every(x => Math.abs(x) <= 3);
}
