/**
 * @param {number[]} original
 * @param {number} m
 * @param {number} n
 * @return {number[][]}
 */
var construct2DArray = function (original, m, n) {
    const result = [];

    if (original.length != m * n) {
        return result;
    }

    for (let i = 0; i < m; i++) {
        result.push(original.slice(i * n, i * n + n));
    }

    return result;
};
