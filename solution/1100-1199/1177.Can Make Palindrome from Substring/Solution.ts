function canMakePaliQueries(s: string, queries: number[][]): boolean[] {
    const n = s.length;
    const ss: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(26).fill(0));
    for (let i = 1; i <= n; ++i) {
        ss[i] = ss[i - 1].slice();
        ++ss[i][s.charCodeAt(i - 1) - 97];
    }
    const ans: boolean[] = [];
    for (const [l, r, k] of queries) {
        let x = 0;
        for (let j = 0; j < 26; ++j) {
            x += (ss[r + 1][j] - ss[l][j]) & 1;
        }
        ans.push(x >> 1 <= k);
    }
    return ans;
}
