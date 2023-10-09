/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    const cs = S.split('').sort();
    const ans = [];
    const n = cs.length;
    const vis = Array(n).fill(false);
    const t = [];
    const dfs = i => {
        if (i === n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && !vis[j - 1] && cs[j] === cs[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.push(cs[j]);
            dfs(i + 1);
            t.pop();
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
