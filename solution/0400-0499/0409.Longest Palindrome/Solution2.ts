function longestPalindrome(s: string): number {
    const odd: Record<string, number> = {};
    let cnt = 0;
    for (const c of s) {
        odd[c] ^= 1;
        cnt += odd[c] ? 1 : -1;
    }
    return cnt ? s.length - cnt + 1 : s.length;
}
