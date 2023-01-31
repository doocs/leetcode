function findRepeatNumber(nums: number[]): number {
    for (let i = 0; ; ++i) {
        while (nums[i] != i) {
            const j = nums[i];
            if (nums[j] == j) {
                return j;
            }
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
}
