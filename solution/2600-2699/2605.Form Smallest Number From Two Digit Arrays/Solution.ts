function minNumber(nums1: number[], nums2: number[]): number {
    let ans = 100;
    for (const a of nums1) {
        for (const b of nums2) {
            if (a === b) {
                ans = Math.min(ans, a);
            } else {
                ans = Math.min(ans, a * 10 + b, b * 10 + a);
            }
        }
    }
    return ans;
}
