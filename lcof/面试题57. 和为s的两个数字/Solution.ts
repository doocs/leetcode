function twoSum(nums: number[], target: number): number[] {
    let l = 0;
    let r = nums.length - 1;
    while (nums[l] + nums[r] !== target) {
        if (nums[l] + nums[r] < target) {
            l++;
        } else {
            r--;
        }
    }
    return [nums[l], nums[r]];
}
