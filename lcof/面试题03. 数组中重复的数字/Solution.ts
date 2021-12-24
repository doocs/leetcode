function findRepeatNumber(nums: number[]): number {
    let n: number = nums.length;
    for (let i: number = 0; i < n; i++) {
        while (nums[i] != i) {
            if (nums[i] == nums[nums[i]]) return nums[i];
            swap(nums, i, nums[i]);
        }
    }
    return -1;
}

function swap(nums: number[], i: number, j: number): void {
    [nums[i], nums[j]] = [nums[j], nums[i]];
}
