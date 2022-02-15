/**
 * @param {string} str
 * @return {number}
 */
var strToInt = function (str) {
    let res = '';
    let l = 1;
    for (let i = 0; i < str.length; i++) {
        if (l && str[i] === ' ') continue;
        if (l && (str[i] === '+' || str[i] === '-')) {
            l = 0;
            res += str[i];
            continue;
        }
        if (str[i].match(/[0-9]/)) {
            l = 0;
            res += str[i];
        } else break;
    }
    res = isNaN(+res) ? 0 : +res;
    if (res > 2147483647) return 2147483647;
    if (res < -2147483648) return -2147483648;
    return res;
};
