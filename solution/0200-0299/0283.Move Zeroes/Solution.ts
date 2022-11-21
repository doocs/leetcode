/**
 Do not return anything, modify nums in-place instead.
 */
function moveZeroes(nums: number[]): void {
    const n = nums.length;
    let i = 0;
    for (let j = 0; j < n; j++) {
        if (nums[j]) {
            if (j > i) {
                [nums[i], nums[j]] = [nums[j], 0];
            }
            i++;
        }
    }
}
