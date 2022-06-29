function minMaxGame(nums: number[]): number {
    while (nums.length > 1) {
        let n = nums.length;
        let tmp = [];
        for (let i = 0; i < n; i += 2) {
            if (i % 4 == 2) {
                tmp.push(Math.max(nums[i], nums[i + 1]));
            } else {
                tmp.push(Math.min(nums[i], nums[i + 1]));
            }
        }
        nums = tmp;
    }
    return nums[0];
}
