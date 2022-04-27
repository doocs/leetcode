/**
 * @param {string} s
 * @return {boolean}
 */
var isPalindrome = function (s) {
    let arr1 = [],
        arr2 = [];
    for (let i = 0; i < s.length; i++) {
        if (s[i] >= 'A' && s[i] <= 'Z') {
            arr1.push(s[i].toLowerCase());
        }
        if ((s[i] >= '0' && s[i] <= '9') || (s[i] >= 'a' && s[i] <= 'z')) {
            arr1.push(s[i]);
        }
    }
    arr2 = [...arr1];
    arr2.reverse();
    return arr1.join('') === arr2.join('');
};
