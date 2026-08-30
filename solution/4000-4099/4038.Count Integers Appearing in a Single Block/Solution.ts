function countSpecialIntegers(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (let i = 0; i < nums.length; ++i) {
        if (i === 0 || nums[i] !== nums[i - 1]) {
            ++cnt[nums[i]];
        }
    }
    return cnt.filter(c => c === 1).length;
}
