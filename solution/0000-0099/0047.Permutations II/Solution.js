/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permuteUnique = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans = [];
    const t = Array(n);
    const vis = Array(n).fill(false);
    const dfs = i => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && nums[j] === nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t[i] = nums[j];
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
