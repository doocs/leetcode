function repeatedSubstringPattern(s: string): boolean {
    const n = s.length;
    for (let i = 0; i < n >> 1; i++) {
        const len = i + 1;
        if (n % len !== 0) {
            continue;
        }
        const t = s.slice(0, len);
        let j: number;
        for (j = len; j < n; j += len) {
            if (s.slice(j, j + len) !== t) {
                break;
            }
        }
        if (j === n) {
            return true;
        }
    }
    return false;
}
