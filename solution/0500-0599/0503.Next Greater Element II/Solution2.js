/**
 * @param {number[]} nums
 * @return {number[]}
 */
var nextGreaterElements = function (nums) {
    const n = nums.length;
    let stk = [];
    let ans = new Array(n).fill(-1);
    for (let i = n * 2 - 1; ~i; --i) {
        const j = i % n;
        while (stk.length && stk[stk.length - 1] <= nums[j]) {
            stk.pop();
        }
        if (stk.length) {
            ans[j] = stk[stk.length - 1];
        }
        stk.push(nums[j]);
    }
    return ans;
};
