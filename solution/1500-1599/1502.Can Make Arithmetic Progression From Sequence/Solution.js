/**
 * @param {number[]} arr
 * @return {boolean}
 */
 var canMakeArithmeticProgression = function(arr) {
    arr.sort((a, b) => a - b);
    for (let i = 1; i < arr.length - 1; i++) {
        if ((arr[i] << 1) != (arr[i - 1] + arr[i + 1])) return false;
    }
    return true;
};