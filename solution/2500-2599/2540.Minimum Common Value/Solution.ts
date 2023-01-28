function getCommon(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        if (nums1[i] === nums2[j]) {
            return nums1[i];
        }
        if (nums1[i] < nums2[j]) {
            i++;
        } else {
            j++;
        }
    }
    return -1;
}
