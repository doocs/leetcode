/**
 * @param {number[]} nums
 * @param {number[][]} queries
 * @return {number[]}
 */
var sumEvenAfterQueries = function (nums, queries) {
    let s = nums.reduce((acc, cur) => acc + (cur % 2 === 0 ? cur : 0), 0);
    const ans = [];
    for (const [v, i] of queries) {
        if (nums[i] % 2 === 0) {
            s -= nums[i];
        }
        nums[i] += v;
        if (nums[i] % 2 === 0) {
            s += nums[i];
        }
        ans.push(s);
    }
    return ans;
};
