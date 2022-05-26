/**
 * @param {number[][]} A
 * @return {number[][]}
 */

/**
 * Author: Mcnwork2018
 */

// 第一种思路，碰到相等的情况才进行取反
var flipAndInvertImage = function (A) {
    const len = A.length;
    for (let k = 0; k < len; k++) {
        let j = len - 1;
        for (let i = 0; i <= ~~(j / 2); i++) {
            if (A[k][i] === A[k][j - i]) {
                A[k][i] === 1
                    ? (A[k][i] = A[k][j - i] = 0)
                    : (A[k][i] = A[k][j - i] = 1);
            }
        }
    }
    return A;
};
// 第二种思路，无论是否相等，对每个值都进行交换并取反
var flipAndInvertImage2 = function (A) {
    for (let i = 0; i < A.length; ++i) {
        let last = A[i].length - 1;
        for (let j = 0; j <= ~~(last / 2); ++j) {
            [A[i][j], A[i][last - j]] = [A[i][last - j] ^ 1, A[i][j] ^ 1];
        }
    }
    return A;
};
