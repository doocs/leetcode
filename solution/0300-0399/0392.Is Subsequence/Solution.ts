function isSubsequence(s: string, t: string): boolean {
    const m = s.length;
    const n = t.length;
    let i = 0;
    for (let j = 0; i < m && j < n; ++j) {
        if (s[i] === t[j]) {
            ++i;
        }
    }
    return i === m;
}
