function removePalindromeSub(s: string): number {
    if (s.length == 0) return 0;
    if (s == s.split('').reverse().join('')) return 1;
    return 2;
}
