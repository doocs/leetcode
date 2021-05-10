/**
 * @param {number[]} g
 * @param {number[]} s
 * @return {number}
 */
var findContentChildren = function (g, s) {
    let len1 = g.length, len2 = s.length;
    if (len2 === 0) return 0;
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    let i = 0, j = 0;
    while (i < len1 && j < len2) {
        while (s[j] < g[i]) j++;
        if (s[j] >= g[i]) {
            i++;
            j++;
        } else break;
    }
    return i;
};