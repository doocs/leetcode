function exchange(nums: number[]): number[] {
    let l = 0;
    let r = nums.length - 1;
    while (l < r) {
        if (nums[l] % 2 === 0) {
            [nums[l], nums[r]] = [nums[r], nums[l]];
            r--;
        } else {
            l++;
        }
    }
    return nums;
}
