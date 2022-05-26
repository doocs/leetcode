/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function (nums) {
    const n = nums.length;
    let res = [];
    let path = [];
    let used = new Array(n).fill(false);
    dfs(0, n, nums, used, path, res);
    return res;
};

function dfs(u, n, nums, used, path, res) {
    if (u == n) {
        res.push(path.slice());
        return;
    }
    for (let i = 0; i < n; ++i) {
        if (!used[i]) {
            path.push(nums[i]);
            used[i] = true;
            dfs(u + 1, n, nums, used, path, res);
            used[i] = false;
            path.pop();
        }
    }
}
