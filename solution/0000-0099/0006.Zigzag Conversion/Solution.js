/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
    if (numRows == 1) return s;
    let arr = new Array(numRows);
    for (let i = 0; i < numRows; i++) arr[i] = [];
    let mi = 0,
        isDown = true;
    for (const c of s) {
        arr[mi].push(c);

        if (mi >= numRows - 1) isDown = false;
        else if (mi <= 0) isDown = true;

        if (isDown) mi++;
        else mi--;
    }
    let ans = [];
    for (let item of arr) {
        ans = ans.concat(item);
    }
    return ans.join('');
};
