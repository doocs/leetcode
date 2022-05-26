/**
 * @param {string} s
 * @return {boolean}
 */
var isNumber = function (s) {
    return s !== ' ' && !isNaN(+s);
};
