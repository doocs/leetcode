/**
 * @param {number[][]} arrays
 * @return {number[]}
 */
var longestCommonSubsequence = function (arrays) {
    let m = new Map();
    let rs = [];
    const len = arrays.length;
    for (let i = 0; i < len; i++) {
        for (let j = 0; j < arrays[i].length; j++) {
            m.set(arrays[i][j], (m.get(arrays[i][j]) || 0) + 1);
        }
    }
    for (let k of m.keys()) {
        if (m.get(k) === len) rs.push(k);
    }
    return rs;
};
