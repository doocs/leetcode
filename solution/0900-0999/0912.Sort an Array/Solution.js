/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortArray = function (nums) {
    function quickSort(left, right) {
        if (left >= right) {
            return;
        }
        let i = left - 1;
        let j = right + 1;
        const x = nums[(left + right) >> 1];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                [nums[i], nums[j]] = [nums[j], nums[i]];
            }
        }
        quickSort(left, j);
        quickSort(j + 1, right);
    }
    const n = nums.length;
    quickSort(0, n - 1);
    return nums;
};
