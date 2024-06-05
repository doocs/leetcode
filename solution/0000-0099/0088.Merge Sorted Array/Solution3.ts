/**
 Do not return anything, modify nums1 in-place instead.
 */
function merge(nums1: number[], m: number, nums2: number[], n: number): void {
    for (let i = 0; i < m; i++) {
        if (nums1[i] > nums2[0]) {
            [nums1[i], nums2[0]] = [nums2[0], nums1[i]];
            for (let j = 0; nums2[j] > nums2[j + 1]; j++) {
                [nums2[j], nums2[j + 1]] = [nums2[j + 1], nums2[j]];
            }
        }
    }

    /** populate last n zero-items */
    for (let i = m, j = 0; j < n; i++, j++) nums1[i] = nums2[j];
}
