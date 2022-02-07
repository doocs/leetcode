function isStraight(nums: number[]): boolean {
    nums.sort((a, b) => a - b);
    let j = 0;
    for (let i = 0; i < 4; i++) {
        if (nums[i] === 0) {
            j++;
        } else if (nums[i] === nums[i + 1]) {
            return false;
        }
    }
    return nums[4] - nums[j] <= 4;
}
