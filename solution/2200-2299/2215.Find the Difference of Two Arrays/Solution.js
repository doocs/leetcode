/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[][]}
 */
var findDifference = function (nums1, nums2) {
    let ans1 = new Set(nums1),
        ans2 = new Set(nums2);
    for (let num of nums1) {
        ans2.delete(num);
    }
    for (let num of nums2) {
        ans1.delete(num);
    }
    return [Array.from(ans1), Array.from(ans2)];
};
