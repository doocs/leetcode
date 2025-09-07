/**
 * @param {number[]} arr
 * @return {boolean}
 */
var canMakeArithmeticProgression = function (arr) {
    arr.sort((a, b) => a - b);
    const n = arr.length;
    const d = arr[1] - arr[0];
    for (let i = 2; i < n; i++) {
        if (arr[i] - arr[i - 1] !== d) {
            return false;
        }
    }
    return true;
};
