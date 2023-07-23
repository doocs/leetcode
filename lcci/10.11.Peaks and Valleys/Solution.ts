/**
 Do not return anything, modify nums in-place instead.
 */
function wiggleSort(nums: number[]): void {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    for (let i = 0; i < n - 1; i += 2) {
        [nums[i], nums[i + 1]] = [nums[i + 1], nums[i]];
    }
}
