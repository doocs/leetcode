function maxDistinct(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (const ch of s) {
        const idx = ch.charCodeAt(0) - 97;
        if (++cnt[idx] === 1) {
            ++ans;
        }
    }
    return ans;
}
