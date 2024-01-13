/**
 * @param {string} s
 * @return {string}
 */
var replaceSpace = function (s) {
    const ans = [];
    for (const c of s) {
        ans.push(c === ' ' ? '%20' : c);
    }
    return ans.join('');
};
