/**
 * @param {number[][]} grid
 * @param {number} x
 * @return {number}
 */
var minOperations = function (grid, x) {
    const nums = grid.flat(2);
    const mod = nums[0] % x;

    if (nums.some(num => num % x !== mod)) {
        return -1;
    }

    nums.sort((a, b) => a - b);
    const mid = nums[Math.floor(nums.length / 2)];
    return nums.reduce((ans, num) => ans + Math.abs(num - mid) / x, 0);
};
