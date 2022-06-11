/**
 * @param {string} s
 * @return {number}
 */
var minFlipsMonoIncr = function (s) {
    const n = s.length;
    let presum = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        presum[i + 1] = presum[i] + (s[i] == '1');
    }
    let ans = presum[n];
    for (let i = 0; i < n; ++i) {
        ans = Math.min(ans, presum[i] + n - i - (presum[n] - presum[i]));
    }
    return ans;
};
