function maxPalindromesAfterOperations(words: string[]): number {
    let s: number = 0;
    let mask: number = 0;
    for (const w of words) {
        s += w.length;
        for (const c of w) {
            mask ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
    }
    s -= (mask.toString(2).match(/1/g) || []).length;
    words.sort((a, b) => a.length - b.length);
    let ans: number = 0;
    for (const w of words) {
        s -= Math.floor(w.length / 2) * 2;
        if (s < 0) {
            break;
        }
        ans++;
    }
    return ans;
}
