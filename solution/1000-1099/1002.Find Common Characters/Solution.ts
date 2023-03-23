function commonChars(words: string[]): string[] {
    const freq: number[] = new Array(26).fill(10000);
    for (const word of words) {
        const t: number[] = new Array(26).fill(0);
        for (const c of word.split('')) {
            ++t[c.charCodeAt(0) - 'a'.charCodeAt(0)];
        }
        for (let i = 0; i < 26; ++i) {
            freq[i] = Math.min(freq[i], t[i]);
        }
    }
    const res: string[] = [];
    for (let i = 0; i < 26; ++i) {
        while (freq[i]-- > 0) {
            res.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
        }
    }
    return res;
}
