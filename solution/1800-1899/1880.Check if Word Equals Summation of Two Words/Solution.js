/**
 * @param {string} firstWord
 * @param {string} secondWord
 * @param {string} targetWord
 * @return {boolean}
 */
var isSumEqual = function (firstWord, secondWord, targetWord) {
    function f(s) {
        let res = 0;
        for (let c of s) {
            res = res * 10 + (c.charCodeAt() - 'a'.charCodeAt());
        }
        return res;
    }
    return f(firstWord) + f(secondWord) == f(targetWord);
};
