function maxProduct(words: string[]): number {
    const mask: Map<number, number> = new Map();
    let ans = 0;
    for (const s of words) {
        const a = s.length;
        let x = 0;
        for (const c of s) {
            x |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
        for (const [y, b] of mask.entries()) {
            if ((x & y) === 0) {
                ans = Math.max(ans, a * b);
            }
        }
        mask.set(x, Math.max(mask.get(x) || 0, a));
    }
    return ans;
}
