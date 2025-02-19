function minSwap(nums1: number[], nums2: number[]): number {
    let [a, b] = [0, 1];
    for (let i = 1; i < nums1.length; ++i) {
        let x = a,
            y = b;
        if (nums1[i - 1] >= nums1[i] || nums2[i - 1] >= nums2[i]) {
            a = y;
            b = x + 1;
        } else {
            b = y + 1;
            if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                a = Math.min(a, y);
                b = Math.min(b, x + 1);
            }
        }
    }
    return Math.min(a, b);
}
