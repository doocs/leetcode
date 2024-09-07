function similarPairs(words: string[]): number {
    let ans = 0;
    const cnt = new Map<number, number>();
    for (const s of words) {
        let x = 0;
        for (const c of s) {
            x |= 1 << (c.charCodeAt(0) - 97);
        }
        ans += cnt.get(x) || 0;
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    return ans;
}
