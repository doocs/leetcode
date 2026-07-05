function canMakeSubsequence(s: string, t: string): boolean {
    const m = s.length,
        n = t.length;
    let i0 = 0,
        i1 = 0,
        j = 0;

    while (i1 < m && j < n) {
        if (s[i1] === t[j]) {
            i1++;
        }

        i1 = Math.max(i1, i0 + 1);

        if (s[i0] === t[j]) {
            i0++;
        }

        j++;
    }

    return i1 === m;
}
