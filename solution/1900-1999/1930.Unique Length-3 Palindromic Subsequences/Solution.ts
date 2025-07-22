function countPalindromicSubsequence(s: string): number {
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let ch = 0; ch < 26; ++ch) {
        const c = String.fromCharCode(ch + a);
        const l = s.indexOf(c);
        const r = s.lastIndexOf(c);
        let mask = 0;
        for (let i = l + 1; i < r; ++i) {
            const j = s.charCodeAt(i) - a;
            if (((mask >> j) & 1) ^ 1) {
                mask |= 1 << j;
                ++ans;
            }
        }
    }
    return ans;
}
