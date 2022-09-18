function longestContinuousSubstring(s: string): number {
    const n = s.length;
    let res = 1;
    let i = 0;
    for (let j = 1; j < n; j++) {
        if (s[j].charCodeAt(0) - s[j - 1].charCodeAt(0) !== 1) {
            res = Math.max(res, j - i);
            i = j;
        }
    }
    return Math.max(res, n - i);
}
