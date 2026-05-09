/**
 * @param {number[]} arr1
 * @param {number[]} arr2
 * @return {number}
 */
var longestCommonPrefix = function (arr1, arr2) {
    const s = new Set();
    for (let x of arr1) {
        for (; x; x = Math.floor(x / 10)) {
            s.add(x);
        }
    }
    let mx = 0;
    for (let x of arr2) {
        for (; x; x = Math.floor(x / 10)) {
            if (s.has(x)) {
                mx = Math.max(mx, x);
                break;
            }
        }
    }
    return mx > 0 ? Math.floor(Math.log10(mx)) + 1 : 0;
};
