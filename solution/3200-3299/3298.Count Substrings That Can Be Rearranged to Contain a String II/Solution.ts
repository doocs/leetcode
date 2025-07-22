function validSubstringCount(word1: string, word2: string): number {
    if (word1.length < word2.length) {
        return 0;
    }
    const cnt: number[] = Array(26).fill(0);
    let need: number = 0;
    for (const c of word2) {
        if (++cnt[c.charCodeAt(0) - 97] === 1) {
            ++need;
        }
    }
    const win: number[] = Array(26).fill(0);
    let [ans, l] = [0, 0];
    for (const c of word1) {
        const i = c.charCodeAt(0) - 97;
        if (++win[i] === cnt[i]) {
            --need;
        }
        while (need === 0) {
            const j = word1[l].charCodeAt(0) - 97;
            if (win[j] === cnt[j]) {
                ++need;
            }
            --win[j];
            ++l;
        }
        ans += l;
    }
    return ans;
}
