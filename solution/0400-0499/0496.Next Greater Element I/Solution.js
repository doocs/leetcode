/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var nextGreaterElement = function (nums1, nums2) {
    let stk = [];
    let m = {};
    for (let v of nums2) {
        while (stk && stk[stk.length - 1] < v) {
            m[stk.pop()] = v;
        }
        stk.push(v);
    }
    return nums1.map(e => m[e] || -1);
};
