/**
 * @param {number[]} arr
 * @return {number}
 */
var lenLongestFibSubseq = function (arr) {
    const mp = new Map();
    const n = arr.length;
    const dp = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        mp.set(arr[i], i);
        for (let j = 0; j < i; ++j) {
            dp[j][i] = 2;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            const d = arr[i] - arr[j];
            if (mp.has(d)) {
                const k = mp.get(d);
                if (k < j) {
                    dp[j][i] = Math.max(dp[j][i], dp[k][j] + 1);
                    ans = Math.max(ans, dp[j][i]);
                }
            }
        }
    }
    return ans;
};
