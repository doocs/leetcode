/**
 * @param {number[][]} matches
 * @return {number[][]}
 */
var findWinners = function (matches) {
    const cnt = new Map();
    for (const [winner, loser] of matches) {
        if (!cnt.has(winner)) {
            cnt.set(winner, 0);
        }
        cnt.set(loser, (cnt.get(loser) || 0) + 1);
    }
    const ans = [[], []];
    for (const [x, v] of cnt) {
        if (v < 2) {
            ans[v].push(x);
        }
    }
    ans[0].sort((a, b) => a - b);
    ans[1].sort((a, b) => a - b);
    return ans;
};
