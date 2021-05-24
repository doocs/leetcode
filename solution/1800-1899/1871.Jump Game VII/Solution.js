/**
 * @param {string} s
 * @param {number} minJump
 * @param {number} maxJump
 * @return {boolean}
 */
 var canReach = function(s, minJump, maxJump) {
    let n = s.length;
    let dp = new Array(n).fill(0);
    let sum = new Array(n + 1).fill(0);
    dp[0] = 1;
    sum[1] = 1;
    for (let i = 1; i < n; i++) {
        if (s.charAt(i) == '0') {
            let left = Math.max(0, i - maxJump);
            let right = i - minJump;
            if (left <= right && sum[right + 1] - sum[left] > 0) {
                dp[i] = 1;
            }
        }
        sum[i + 1] = sum[i] + dp[i];
    }
    return dp.pop();
};