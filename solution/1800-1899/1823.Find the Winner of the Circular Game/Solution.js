/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var findTheWinner = function (n, k) {
    if (n === 1) {
        return 1;
    }
    const ans = (k + findTheWinner(n - 1, k)) % n;
    return ans ? ans : n;
};
