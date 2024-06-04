function longestPalindrome(s: string): number {
    const odd: Record<string, number> = {};
    let c = 0;

    for (const ch of s) {
        odd[ch] ^= 1;
        c += odd[ch] ? 1 : -1;
    }

    return c ? s.length - c + 1 : s.length;
}
