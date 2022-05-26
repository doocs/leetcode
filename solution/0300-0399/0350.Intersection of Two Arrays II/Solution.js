/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = function (nums1, nums2) {
    const counter = {};
    for (const num of nums1) {
        counter[num] = (counter[num] || 0) + 1;
    }
    let res = [];
    for (const num of nums2) {
        if (counter[num] > 0) {
            res.push(num);
            counter[num] -= 1;
        }
    }
    return res;
};
