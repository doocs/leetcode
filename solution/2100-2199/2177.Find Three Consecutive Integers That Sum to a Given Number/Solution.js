/**
 * @param {number} num
 * @return {number[]}
 */
var sumOfThree = function (num) {
    if (num % 3) {
        return [];
    }
    const x = Math.floor(num / 3);
    return [x - 1, x, x + 1];
};
