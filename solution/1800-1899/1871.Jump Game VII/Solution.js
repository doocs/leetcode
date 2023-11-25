/**
 * @param {string} s
 * @param {number} minJump
 * @param {number} maxJump
 * @return {boolean}
 */
var canReach = function (s, minJump, maxJump) {
    const n = s.length;
    const pre = Array(n + 1).fill(0);
    pre[1] = 1;
    const f = Array(n).fill(false);
    f[0] = true;
    for (let i = 1; i < n; ++i) {
        if (s[i] === '0') {
            const [l, r] = [Math.max(0, i - maxJump), i - minJump];
            f[i] = l <= r && pre[r + 1] - pre[l] > 0;
        }
        pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
    }
    return f[n - 1];
};
