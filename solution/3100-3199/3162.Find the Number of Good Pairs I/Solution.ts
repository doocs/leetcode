function numberOfPairs(nums1: number[], nums2: number[], k: number): number {
    let ans = 0;
    for (const x of nums1) {
        for (const y of nums2) {
            if (x % (y * k) === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
