/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
 var nextGreaterElement = function(nums1, nums2) {
    let stack = [];
    let nextBigger = {};
    for (let num of nums2) {
        while(stack.length > 0 && stack[stack.length - 1] < num) {
            nextBigger[stack.pop()] = num;
        }
        stack.push(num);
    }
    let res = nums1.map(d => nextBigger[d] || -1);
    return res;
};