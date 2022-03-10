/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    let left = 0;
    let right = numbers.length - 1;
    while (right > 0) {
        let tem = target - numbers[right];
        while (left < right) {
            if (numbers[left] == tem) {
                break;
            }
            left++;
        }
        if (left < right) {
            break;
        }
        left = 0;
        right--;
    }
    return [left + 1, right + 1];
};
