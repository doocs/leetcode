/**
 * @param {string} firstWord
 * @param {string} secondWord
 * @param {string} targetWord
 * @return {boolean}
 */
var isSumEqual = function (firstWord, secondWord, targetWord) {
    const f = s => {
        let ans = 0;
        for (const c of s) {
            ans = ans * 10 + c.charCodeAt(0) - 97;
        }
        return ans;
    };
    return f(firstWord) + f(secondWord) == f(targetWord);
};
