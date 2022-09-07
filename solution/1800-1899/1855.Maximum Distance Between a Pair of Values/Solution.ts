function maxDistance(nums1: number[], nums2: number[]): number {
    let ans = 0;
    const m = nums1.length;
    const n = nums2.length;
    for (let i = 0, j = 0; i < m; ++i) {
        while (j < n && nums1[i] <= nums2[j]) {
            j++;
        }
        ans = Math.max(ans, j - i - 1);
    }
    return ans;
}
