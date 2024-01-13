function containsDuplicate(nums: number[]): boolean {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    for (let i = 1; i < n; i++) {
        if (nums[i - 1] === nums[i]) {
            return true;
        }
    }
    return false;
}
