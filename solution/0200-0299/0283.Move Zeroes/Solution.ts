/**
 Do not return anything, modify nums in-place instead.
 */
function moveZeroes(nums: number[]): void {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i]) {
            [nums[i], nums[k]] = [nums[k], nums[i]];
            ++k;
        }
    }
}
