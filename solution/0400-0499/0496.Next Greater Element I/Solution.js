/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
 var nextGreaterElement = function(nums1, nums2) {
    let stk = [];
    let nextGreater = {};
    for (let num of nums2) {
        while (stk && stk[stk.length - 1] < num) {
            nextGreater[stk.pop()] = num;
        }
        stk.push(num);
    }
    return nums1.map(e => nextGreater[e] || -1);
};