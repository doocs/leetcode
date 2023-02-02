/**
 * @param {string} s
 * @return {string[]}
 */
var permutation = function (s) {
    const cs = s.split('');
    const ans = [];
    const n = s.length;
    const dfs = i => {
        if (i == n - 1) {
            ans.push(cs.join(''));
            return;
        }
        const vis = new Set();
        for (let j = i; j < n; ++j) {
            if (!vis.has(cs[j])) {
                vis.add(cs[j]);
                [cs[i], cs[j]] = [cs[j], cs[i]];
                dfs(i + 1);
                [cs[i], cs[j]] = [cs[j], cs[i]];
            }
        }
    };
    dfs(0);
    return ans;
};
