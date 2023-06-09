/**
 * @param {number[][]} arrays
 * @return {number[]}
 */
var longestCommonSubsequence = function (arrays) {
    const m = new Map();
    const rs = [];
    const len = arrays.length;
    for (let i = 0; i < len; i++) {
        for (let j = 0; j < arrays[i].length; j++) {
            m.set(arrays[i][j], (m.get(arrays[i][j]) || 0) + 1);
            if (m.get(arrays[i][j]) === len) rs.push(arrays[i][j]);
        }
    }
    return rs;
};
