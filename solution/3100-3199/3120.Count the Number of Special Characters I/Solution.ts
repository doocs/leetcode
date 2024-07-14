function numberOfSpecialChars(word: string): number {
    const s: boolean[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => false);
    for (let i = 0; i < word.length; ++i) {
        s[word.charCodeAt(i)] = true;
    }
    let ans: number = 0;
    for (let i = 0; i < 26; ++i) {
        if (s['a'.charCodeAt(0) + i] && s['A'.charCodeAt(0) + i]) {
            ++ans;
        }
    }
    return ans;
}
