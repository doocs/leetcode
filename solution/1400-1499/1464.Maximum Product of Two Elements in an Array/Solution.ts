function maxProduct(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < 2; i++) {
        let maxIdx = i;
        for (let j = i + 1; j < n; j++) {
            if (nums[j] > nums[maxIdx]) {
                maxIdx = j;
            }
        }
        [nums[i], nums[maxIdx]] = [nums[maxIdx], nums[i]];
    }
    return (nums[0] - 1) * (nums[1] - 1);
}
