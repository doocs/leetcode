/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var minSteps = function (s, t) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        ++cnt[i];
    }
    let ans = 0;
    for (const c of t) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        ans += --cnt[i] < 0;
    }
    return ans;
};
