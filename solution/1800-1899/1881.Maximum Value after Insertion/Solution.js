/**
 * @param {string} n
 * @param {number} x
 * @return {string}
 */
var maxValue = function (n, x) {
    let i = 0;
    if (n[0] === '-') {
        i++;
        while (i < n.length && +n[i] <= x) {
            i++;
        }
    } else {
        while (i < n.length && +n[i] >= x) {
            i++;
        }
    }
    return n.slice(0, i) + x + n.slice(i);
};
