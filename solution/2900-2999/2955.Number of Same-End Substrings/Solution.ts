function sameEndSubstringCount(s: string, queries: number[][]): number[] {
    const n: number = s.length;
    const cnt: number[][] = Array.from({ length: 26 }, () => Array(n + 1).fill(0));
    for (let j = 1; j <= n; j++) {
        for (let i = 0; i < 26; i++) {
            cnt[i][j] = cnt[i][j - 1];
        }
        cnt[s.charCodeAt(j - 1) - 'a'.charCodeAt(0)][j]++;
    }
    const ans: number[] = [];
    for (const [l, r] of queries) {
        ans.push(r - l + 1);
        for (let i = 0; i < 26; i++) {
            const x: number = cnt[i][r + 1] - cnt[i][l];
            ans[ans.length - 1] += (x * (x - 1)) / 2;
        }
    }
    return ans;
}
