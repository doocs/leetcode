function longestPalindrome(s: string): number {
    const map = new Map();
    for (const c of s) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    let hasOdd = false;
    let res = 0;
    for (const v of map.values()) {
        res += v;
        if (v & 1) {
            hasOdd = true;
            res--;
        }
    }
    return res + (hasOdd ? 1 : 0);
}
