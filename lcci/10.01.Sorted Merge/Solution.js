/**
 * @param {number[]} A
 * @param {number} m
 * @param {number[]} B
 * @param {number} n
 * @return {void} Do not return anything, modify A in-place instead.
 */
var merge = function (A, m, B, n) {
    let i = m - 1,
        j = n - 1;
    for (let k = A.length - 1; k >= 0; k--) {
        if (k == i) return;
        if (i < 0 || A[i] <= B[j]) {
            A[k] = B[j];
            j--;
        } else {
            A[k] = A[i];
            i--;
        }
    }
};
