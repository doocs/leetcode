function makeSmallestPalindrome(s: string): string {
    const cs = s.split('');
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        cs[i] = cs[j] = s[i] < s[j] ? s[i] : s[j];
    }
    return cs.join('');
}
