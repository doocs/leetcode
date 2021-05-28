/**
 * @param {number[]} nums
 * @return {number[][]}
 */
 var permute = function(nums) {
    let res = [];
    let solution = [];
    let record = new Array(nums.length).fill(false);
    dfs(nums, 0, record, solution, res);
    return res;
};

function dfs (nums, depth, record, solution, res) {
    if (depth == nums.length) {
        res.push(solution.slice());
        return;
    }
    for (let i = 0; i < nums.length; i++) {
        if (!record[i]) {
            solution.push(nums[i]);
            record[i] = true;
            dfs(nums, depth + 1, record, solution, res);
            solution.pop();
            record[i] = false;
        }
    }
}