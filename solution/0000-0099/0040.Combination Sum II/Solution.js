/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function (candidates, target) {
    candidates.sort((a, b) => a - b);
    const n = candidates.length;
    const t = [];
    const ans = [];
    const dfs = (i, s) => {
        if (s > target) {
            return;
        }
        if (s === target) {
            ans.push([...t]);
            return;
        }
        for (let j = i; j < n; j++) {
            const num = candidates[j];
            if (j > i && num === candidates[j - 1]) {
                continue;
            }
            t.push(num);
            dfs(j + 1, s + num);
            t.pop();
        }
    };
    dfs(0, 0);
    return ans;
};
