/**
 * @param {string} s
 * @return {string}
 */
var makeFancyString = function (s) {
    const ans = [];
    for (let i = 0; i < s.length; ++i) {
        if (s[i] !== s[i - 1] || s[i] !== s[i - 2]) {
            ans.push(s[i]);
        }
    }
    return ans.join('');
};
