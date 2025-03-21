/**
 * @param {number} n
 * @param {number} k
 * @return {string}
 */
var getHappyString = function (n, k) {
    const ans = [];
    const s = [];
    const dfs = () => {
        if (s.length === n) {
            ans.push(s.join(''));
            return;
        }
        if (ans.length >= k) {
            return;
        }
        for (const c of 'abc') {
            if (!s.length || s.at(-1) !== c) {
                s.push(c);
                dfs();
                s.pop();
            }
        }
    };
    dfs();
    return ans[k - 1] ?? '';
};
