function closeStrings(word1: string, word2: string): boolean {
    const cnt1 = Array(26).fill(0);
    const cnt2 = Array(26).fill(0);
    for (const c of word1) {
        ++cnt1[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (const c of word2) {
        ++cnt2[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (let i = 0; i < 26; ++i) {
        if ((cnt1[i] === 0) !== (cnt2[i] === 0)) {
            return false;
        }
    }
    cnt1.sort((a, b) => a - b);
    cnt2.sort((a, b) => a - b);
    return cnt1.join('.') === cnt2.join('.');
}
