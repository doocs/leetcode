function makeSmallestPalindrome(s: string): string {
    const cs = s.split('');
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        cs[i] = cs[j] = String.fromCharCode(Math.min(cs[i].charCodeAt(0), cs[j].charCodeAt(0)));
    }
    return cs.join('');
}
