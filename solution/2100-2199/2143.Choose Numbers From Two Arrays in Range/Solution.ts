function countSubranges(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const s1 = nums1.reduce((a, b) => a + b, 0);
    const s2 = nums2.reduce((a, b) => a + b, 0);
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(s1 + s2 + 1).fill(0));
    const mod = 1e9 + 7;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const [a, b] = [nums1[i], nums2[i]];
        f[i][a + s2]++;
        f[i][-b + s2]++;
        if (i) {
            for (let j = 0; j <= s1 + s2; ++j) {
                if (j >= a) {
                    f[i][j] = (f[i][j] + f[i - 1][j - a]) % mod;
                }
                if (j + b <= s1 + s2) {
                    f[i][j] = (f[i][j] + f[i - 1][j + b]) % mod;
                }
            }
        }
        ans = (ans + f[i][s2]) % mod;
    }
    return ans;
}
