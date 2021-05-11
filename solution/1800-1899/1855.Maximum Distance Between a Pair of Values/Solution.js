/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
 var maxDistance = function(nums1, nums2) {
    let res = 0;
    for (let i = 0; i < nums1.length; i++) {
        let left = 0, right = nums2.length - 1;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums2[mid] >= nums1[i]) {
                res = Math.max(res, mid - i);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    return res;
};