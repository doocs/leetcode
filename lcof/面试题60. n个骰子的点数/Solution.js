/**
 * @param {number} n
 * @return {number[]}
 */
var twoSum = function (n) {
    function backtrack(sum, time) {
        if (time === n) {
            res[sum]++;
            return;
        }
        for (let i = 1; i <= 6; i++) {
            backtrack(sum + i, time + 1);
        }
    }
    let len = n * 6;
    let t = 6 ** n;
    let res = new Array(len + 1).fill(0);
    backtrack(0, 0);
    return res.slice(n).map(e => e / t);
};
