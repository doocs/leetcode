function countKConstraintSubstrings(s: string, k: number, queries: number[][]): number[] {
    const cnt: [number, number] = [0, 0];
    const n = s.length;
    const d: number[] = Array(n).fill(n);
    const pre: number[] = Array(n + 1).fill(0);
    for (let i = 0, j = 0; j < n; ++j) {
        cnt[+s[j]]++;
        while (Math.min(cnt[0], cnt[1]) > k) {
            d[i] = j;
            cnt[+s[i++]]--;
        }
        pre[j + 1] = pre[j] + j - i + 1;
    }
    const ans: number[] = [];
    for (const [l, r] of queries) {
        const p = Math.min(r + 1, d[l]);
        const a = ((1 + p - l) * (p - l)) / 2;
        const b = pre[r + 1] - pre[p];
        ans.push(a + b);
    }
    return ans;
}
