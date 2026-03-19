/**
 * @param {string} s
 * @return {number}
 */
var minimumDeletions = function (s) {
    let lb = 0,
        ra = 0;
    let n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] === 'a') {
            ++ra;
        }
    }
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ra -= s[i] === 'a' ? 1 : 0;
        ans = Math.min(ans, lb + ra);
        lb += s[i] === 'b' ? 1 : 0;
    }
    return ans;
};
