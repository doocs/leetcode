/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    const n = S.length;
    const vis = Array(n).fill(false);
    const ans = [];
    const t = Array(n).fill('');
    const dfs = i => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j]) {
                continue;
            }
            vis[j] = true;
            t[i] = S[j];
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
