/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function (s) {
    return s
        .split(' ')
        .map(t => t.split('').reverse().join(''))
        .join(' ');
};
