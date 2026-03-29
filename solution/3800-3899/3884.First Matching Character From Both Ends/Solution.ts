function firstMatchingIndex(s: string): number {
    const n = s.length;
    for (let i = 0; i < Math.floor(n / 2) + 1; i++) {
        if (s[i] === s[n - i - 1]) {
            return i;
        }
    }
    return -1;
}
