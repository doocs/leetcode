/**
 * @param {number} n
 * @param {number} m
 * @return {number}
 */
var lastRemaining = function (n, m) {
    // 约瑟夫环
    let res = 0;
    for (let i = 1; i <= n; i++) {
        res = (res + m) % i;
    }
    return res;
};
