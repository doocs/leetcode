/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function (nums) {
    const n = nums.length;
    const ans = [];
    const t = [];
    const vis = new Array(n).fill(false);
    function dfs(i) {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t.push(nums[j]);
                dfs(i + 1);
                vis[j] = false;
                t.pop();
            }
        }
    }
    dfs(0);
    return ans;
};
