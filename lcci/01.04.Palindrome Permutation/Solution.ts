function canPermutePalindrome(s: string): boolean {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    return Object.values(cnt).filter(v => v % 2 === 1).length < 2;
}
