/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var nextGreaterElement = function (nums1, nums2) {
    const stk = [];
    const d = {};
    for (const x of nums2.reverse()) {
        while (stk.length && stk.at(-1) < x) {
            stk.pop();
        }
        d[x] = stk.length ? stk.at(-1) : -1;
        stk.push(x);
    }
    return nums1.map(x => d[x]);
};
