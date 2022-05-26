const generate = function (numRows) {
    let arr = [];
    for (let i = 0; i < numRows; i++) {
        let row = [];
        row[0] = 1;
        row[i] = 1;
        for (let j = 1; j < row.length - 1; j++) {
            row[j] = arr[i - 1][j - 1] + arr[i - 1][j];
        }
        arr.push(row);
    }
    return arr;
};

/**
 *  Author: Mcnwork2018
 */

var generate = function (numRows) {
    if (numRows === 0) return [];
    if (numRows === 1) return [[1]];
    if (numRows === 2) return [[1], [1, 1]];
    let triangleArray = [[1], [1, 1]];
    for (let i = 2; i < numRows; ++i) {
        triangleArray[i] = [];
        for (let j = 0; j < i + 1; ++j) {
            if (j === 0 || j === i) {
                triangleArray[i][j] = 1;
            } else {
                triangleArray[i][j] =
                    triangleArray[i - 1][j - 1] + triangleArray[i - 1][j];
            }
        }
    }
    return triangleArray;
};
