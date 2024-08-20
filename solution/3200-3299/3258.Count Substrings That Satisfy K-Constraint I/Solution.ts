function countKConstraintSubstrings(s: string, k: number): number {
    let [cnt0, cnt1, ans, l] = [0, 0, 0, 0];
    for (let r = 0; r < s.length; ++r) {
        const x = s[r] === '1' ? 1 : 0;
        cnt0 += x ^ 1;
        cnt1 += x;
        while (cnt0 > k && cnt1 > k) {
            const y = s[l++] === '1' ? 1 : 0;
            cnt0 -= y ^ 1;
            cnt1 -= y;
        }
        ans += r - l + 1;
    }
    return ans;
}
