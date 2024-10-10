/**
 * @param {number[]} nums
 * @return {number}
 */
var findNumbers = function (nums) {
    return nums.filter(x => x.toString().length % 2 === 0).length;
};
