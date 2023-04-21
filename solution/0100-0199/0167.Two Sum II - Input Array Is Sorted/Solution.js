/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    for (let i = 0, j = numbers.length - 1; ; ) {
        const x = numbers[i] + numbers[j];
        if (x === target) {
            return [i + 1, j + 1];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
};
