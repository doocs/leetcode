/**
 * @param {number[]} arr
 * @return {number}
 */
var countElements = function (arr) {
    const s = new Set();
    for (const x of arr) {
        s.add(x);
    }
    let ans = 0;
    for (const x of arr) {
        if (s.has(x + 1)) {
            ++ans;
        }
    }
    return ans;
};
