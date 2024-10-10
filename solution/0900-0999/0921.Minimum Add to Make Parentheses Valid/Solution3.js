/**
 * @param {string} s
 * @return {number}
 */
var minAddToMakeValid = function (s) {
    const l = s.length;
    s = s.replace('()', '');
    return s.length === l ? l : minAddToMakeValid(s);
};
