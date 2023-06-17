/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    let i = -1;
    let j = nums.length;
    let k = 0;
    while (k < j) {
        if (nums[k] === 0) {
            ++i;
            [nums[i], nums[k]] = [nums[k], nums[i]];
            ++k;
        } else if (nums[k] === 2) {
            --j;
            [nums[j], nums[k]] = [nums[k], nums[j]];
        } else {
            ++k;
        }
    }
}
