/**
 * @param {number[]} nums
 * @return {number[]}
 */
var nextGreaterElements = function (nums) {
    const n = nums.length;
    let stk = [];
    let ans = new Array(n).fill(-1);
    for (let i = 0; i < n << 1; i++) {
        const j = i % n;
        while (stk.length && nums[stk[stk.length - 1]] < nums[j]) {
            ans[stk.pop()] = nums[j];
        }
        stk.push(j);
    }
    return ans;
};
