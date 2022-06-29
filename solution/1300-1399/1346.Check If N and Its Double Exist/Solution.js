/**
 * @param {number[]} arr
 * @return {boolean}
 */
var checkIfExist = function (arr) {
    const s = new Set();
    for (const v of arr) {
        if (s.has(v << 1) || s.has(v / 2)) {
            return true;
        }
        s.add(v);
    }
    return false;
};
