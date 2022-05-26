/**
 * @param {number[]} A
 * @return {number}
 */
var largestUniqueNumber = function (A) {
    let counter = {};
    for (const a of A) {
        counter[a] = (counter[a] || 0) + 1;
    }
    for (let i = 1000; i >= 0; --i) {
        if (counter[i] == 1) {
            return i;
        }
    }
    return -1;
};
