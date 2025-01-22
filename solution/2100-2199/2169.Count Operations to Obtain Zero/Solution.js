/**
 * @param {number} num1
 * @param {number} num2
 * @return {number}
 */
var countOperations = function (num1, num2) {
    let ans = 0;
    for (; num1 && num2; ++ans) {
        if (num1 >= num2) {
            num1 -= num2;
        } else {
            num2 -= num1;
        }
    }
    return ans;
};
