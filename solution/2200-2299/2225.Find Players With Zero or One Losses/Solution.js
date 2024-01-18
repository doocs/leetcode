/**
 * @param {number[][]} matches
 * @return {number[][]}
 */
var findWinners = function (matches) {
    const cnt = new Map();
    for (const [a, b] of matches) {
        cnt.set(a, cnt.has(a) ? cnt.get(a) : 0);
        cnt.set(b, (cnt.get(b) || 0) + 1);
    }
    const ans = [[], []];
    for (let [u, v] of cnt.entries()) {
        if (v < 2) {
            ans[v].push(u);
        }
    }
    ans[0].sort((a, b) => a - b);
    ans[1].sort((a, b) => a - b);
    return ans;
};
