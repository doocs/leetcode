/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array[]}
 */
var chunk = function (arr, size) {
    const ans = [];
    for (let i = 0, n = arr.length; i < n; i += size) {
        ans.push(arr.slice(i, i + size));
    }
    return ans;
};
