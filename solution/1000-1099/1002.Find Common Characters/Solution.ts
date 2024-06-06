function commonChars(words: string[]): string[] {
    const freq: number[] = Array(26).fill(Number.POSITIVE_INFINITY);
    const aCode = 'a'.charCodeAt(0);
    for (const word of words) {
        const t: number[] = Array(26).fill(0);
        for (const c of word) {
            ++t[c.charCodeAt(0) - aCode];
        }
        for (let i = 0; i < 26; ++i) {
            freq[i] = Math.min(freq[i], t[i]);
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < 26; ++i) {
        if (freq[i]) {
            ans.push(...String.fromCharCode(i + aCode).repeat(freq[i]));
        }
    }
    return ans;
}
