/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function (nums) {
    let prev = [];
    let res = [];
    dfs(nums, 0, prev, res);
    return res;
};

function dfs(nums, depth, prev, res) {
    res.push(prev.slice());
    for (let i = depth; i < nums.length; i++) {
        prev.push(nums[i]);
        depth++;
        dfs(nums, depth, prev, res);
        prev.pop();
    }
}
