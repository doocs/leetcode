/**
 * @param {number} n
 * @return {boolean}
 */
var isThree = function (n) {
    let cnt = 0;
    for (let i = 1; i <= n / i; ++i) {
        if (n % i == 0) {
            cnt += ~~(n / i) == i ? 1 : 2;
        }
    }
    return cnt == 3;
};
