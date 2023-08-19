function maxProduct(words: string[]): number {
    const n = words.length;
    const mask: number[] = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (const c of words[i]) {
            mask[i] |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            if ((mask[i] & mask[j]) === 0) {
                ans = Math.max(ans, words[i].length * words[j].length);
            }
        }
    }
    return ans;
}
