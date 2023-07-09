function maxNonDecreasingLength(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    let [f, g, ans] = [1, 1, 1];
    for (let i = 1; i < n; ++i) {
        let [ff, gg] = [1, 1];
        if (nums1[i] >= nums1[i - 1]) {
            ff = Math.max(ff, f + 1);
        }
        if (nums1[i] >= nums2[i - 1]) {
            ff = Math.max(ff, g + 1);
        }
        if (nums2[i] >= nums1[i - 1]) {
            gg = Math.max(gg, f + 1);
        }
        if (nums2[i] >= nums2[i - 1]) {
            gg = Math.max(gg, g + 1);
        }
        f = ff;
        g = gg;
        ans = Math.max(ans, f, g);
    }
    return ans;
}
