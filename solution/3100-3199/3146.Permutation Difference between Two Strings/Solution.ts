function findPermutationDifference(s: string, t: string): number {
    const d: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        d[s.charCodeAt(i) - 97] = i;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.abs(d[t.charCodeAt(i) - 97] - i);
    }
    return ans;
}
