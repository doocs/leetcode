/**
 * @param {string} s
 * @param {number[]} indices
 * @return {string}
 */
var restoreString = function (s, indices) {
    let rs = [];
    for (let i = 0; i < s.length; i++) {
        rs[indices[i]] = s[i];
    }
    return rs.join('');
};
