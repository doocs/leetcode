function transformArray(nums: number[]): number[] {
    const even = nums.filter(x => x % 2 === 0).length;
    for (let i = 0; i < even; ++i) {
        nums[i] = 0;
    }
    for (let i = even; i < nums.length; ++i) {
        nums[i] = 1;
    }
    return nums;
}
