function isSubsequence(s: string, t: string): boolean {
    let m = s.length,
        n = t.length;
    let i = 0;
    for (let j = 0; j < n && i < m; ++j) {
        if (s.charAt(i) == t.charAt(j)) {
            ++i;
        }
    }
    return i == m;
}
