function almostPalindromic(s: string): number {
    const n = s.length;

    const f = (l: number, r: number): number => {
        while (l >= 0 && r < n && s[l] === s[r]) {
            l--;
            r++;
        }

        let l1 = l - 1,
            r1 = r;
        let l2 = l,
            r2 = r + 1;

        while (l1 >= 0 && r1 < n && s[l1] === s[r1]) {
            l1--;
            r1++;
        }
        while (l2 >= 0 && r2 < n && s[l2] === s[r2]) {
            l2--;
            r2++;
        }

        return Math.min(n, Math.max(r1 - l1 - 1, r2 - l2 - 1));
    };

    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, f(i, i));
        ans = Math.max(ans, f(i, i + 1));
    }

    return ans;
}
