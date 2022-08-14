/**
 * @param {number[]} g
 * @param {number[]} s
 * @return {number}
 */
var findContentChildren = function (g, s) {
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    let i = 0;
    let j = 0;
    for (; i < g.length; ++i) {
        while (j < s.length && s[j] < g[i]) {
            ++j;
        }
        if (j >= s.length) {
            break;
        }
        ++j;
    }
    return i;
};
