function findMagicIndex(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i < n) {
        if (nums[i] === i) {
            return i;
        }
        i = Math.max(nums[i], i + 1);
    }
    return -1;
}
