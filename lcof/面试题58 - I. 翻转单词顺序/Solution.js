/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function (s) {
    return s
        .split(' ')
        .reduce((acc, cur) => (cur !== '' ? acc.concat(cur) : acc), [])
        .reverse()
        .join(' ');
};
