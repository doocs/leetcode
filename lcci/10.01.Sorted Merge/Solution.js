/**
 * @param {number[]} A
 * @param {number} m
 * @param {number[]} B
 * @param {number} n
 * @return {void} Do not return anything, modify A in-place instead.
 */
var merge = function (A, m, B, n) {
    let [i, j] = [m - 1, n - 1];
    for (let k = A.length - 1; ~k; --k) {
        if (j < 0 || (i >= 0 && A[i] > B[j])) {
            A[k] = A[i--];
        } else {
            A[k] = B[j--];
        }
    }
};
