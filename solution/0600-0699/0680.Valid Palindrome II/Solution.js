/**
 * @param {string} s
 * @return {boolean}
 */
var validPalindrome = function (s) {
    let check = function (i, j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
};
