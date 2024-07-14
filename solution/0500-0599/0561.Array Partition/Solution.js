/**
 * @param {number[]} nums
 * @return {number}
 */
var arrayPairSum = function (nums) {
    nums.sort((a, b) => a - b);
    return nums.reduce((acc, cur, i) => (i % 2 === 0 ? acc + cur : acc), 0);
};
