function makeAntiPalindrome(s: string): string {
    const cs: string[] = s.split('').sort();
    const n: number = cs.length;
    const m = n >> 1;
    if (cs[m] === cs[m - 1]) {
        let i = m;
        for (; i < n && cs[i] === cs[i - 1]; i++);
        for (let j = m; j < n && cs[j] === cs[n - j - 1]; ++i, ++j) {
            if (i >= n) {
                return '-1';
            }
            [cs[j], cs[i]] = [cs[i], cs[j]];
        }
    }
    return cs.join('');
}
