function longestPalindrome(s: string, t: string): number {
    function expand(s: string, g: number[], l: number, r: number): void {
        while (l >= 0 && r < s.length && s[l] === s[r]) {
            g[l] = Math.max(g[l], r - l + 1);
            l--;
            r++;
        }
    }

    function calc(s: string): number[] {
        const n = s.length;
        const g: number[] = Array(n).fill(0);
        for (let i = 0; i < n; i++) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }

    const m = s.length,
        n = t.length;
    t = t.split('').reverse().join('');
    const g1 = calc(s);
    const g2 = calc(t);
    let ans = Math.max(...g1, ...g2);

    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] === t[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
                ans = Math.max(ans, f[i][j] * 2 + (i >= m ? 0 : g1[i]));
                ans = Math.max(ans, f[i][j] * 2 + (j >= n ? 0 : g2[j]));
            }
        }
    }

    return ans;
}
