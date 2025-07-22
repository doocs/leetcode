/**
 * @param {number} num
 * @return {number}
 */
var findComplement = function (num) {
    return num ^ (2 ** num.toString(2).length - 1);
};
