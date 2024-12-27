function maximumMatchingIndices(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    let ans: number = 0;
    for (let k = 0; k < n; ++k) {
        let t: number = 0;
        for (let i = 0; i < n; ++i) {
            if (nums1[(i + k) % n] === nums2[i]) {
                ++t;
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
