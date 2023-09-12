/**
 * @param {string} s
 * @return {boolean}
 */
var validPalindrome = function (s) {
    const check = (i, j) => {
        for (; i < j; ++i, --j) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
};
