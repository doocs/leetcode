/**
 * @param {string} s
 * @return {number}
 */
var minimumDeletions = function (s) {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    let b = 0;
    for (let i = 1; i <= n; ++i) {
        if (s[i - 1] === 'b') {
            f[i] = f[i - 1];
            ++b;
        } else {
            f[i] = Math.min(f[i - 1] + 1, b);
        }
    }
    return f[n];
};
