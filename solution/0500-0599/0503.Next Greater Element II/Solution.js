/**
 * @param {number[]} nums
 * @return {number[]}
 */
 var nextGreaterElements = function(nums) {
    let n = nums.length;
    let stack = [];
    let res = new Array(n).fill(-1);
    for (let i = 0; i < 2 * n; i++) {
        let cur = nums[i % n];
        while (stack.length > 0 && nums[stack[stack.length - 1]] < cur) {
            res[stack.pop()] = cur;
        }
        stack.push(i % n);
    }
    return res;
};