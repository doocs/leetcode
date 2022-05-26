/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function (nums, k) {
    let ans = [];
    let q = [];
    for (let i = 0; i < nums.length; ++i) {
        if (q && i - k + 1 > q[0]) {
            q.shift();
        }
        while (q && nums[q[q.length - 1]] <= nums[i]) {
            q.pop();
        }
        q.push(i);
        if (i >= k - 1) {
            ans.push(nums[q[0]]);
        }
    }
    return ans;
};
