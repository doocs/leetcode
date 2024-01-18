/**
 * @param {string} num
 * @return {string}
 */
var largestOddNumber = function (num) {
    for (let i = num.length - 1; ~i; --i) {
        if (Number(num[i]) & 1) {
            return num.slice(0, i + 1);
        }
    }
    return '';
};
