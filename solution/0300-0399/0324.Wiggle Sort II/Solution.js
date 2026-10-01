/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var wiggleSort = function (nums) {
    const arr = nums.slice().sort((a, b) => a - b);
    const n = arr.length;
    let i = (n - 1) >> 1;
    let j = n - 1;
    for (let k = 0; k < n; ++k) {
        if (k % 2 === 0) {
            nums[k] = arr[i--];
        } else {
            nums[k] = arr[j--];
        }
    }
};
