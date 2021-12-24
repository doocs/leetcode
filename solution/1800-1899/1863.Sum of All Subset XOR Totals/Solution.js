/**
 * @param {number[]} nums
 * @return {number}
 */
var subsetXORSum = function (nums) {
    let res = [];
    let prev = 0;
    dfs(nums, 0, prev, res);
    return res.reduce((a, c) => a + c, 0);
};

function dfs(nums, depth, prev, res) {
    res.push(prev);
    for (let i = depth; i < nums.length; i++) {
        prev ^= nums[i];
        depth++;
        dfs(nums, depth, prev, res);
        // bracktrack
        prev ^= nums[i];
    }
}
