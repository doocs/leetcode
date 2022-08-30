function exchange(nums: number[]): number[] {
    let i = 0;
    let j = nums.length - 1;
    while (i < j) {
        while (i < j && nums[i] % 2 == 1) {
            i++;
        }
        while (i < j && nums[j] % 2 == 0) {
            --j;
        }
        const t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    return nums;
}
