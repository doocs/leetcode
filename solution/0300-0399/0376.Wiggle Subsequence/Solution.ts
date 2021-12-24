function wiggleMaxLength(nums: number[]): number {
    let up = 1,
        down = 1;
    for (let i = 1; i < nums.length; ++i) {
        let prev = nums[i - 1],
            cur = nums[i];
        if (cur > prev) {
            up = Math.max(up, down + 1);
        } else if (cur < prev) {
            down = Math.max(down, up + 1);
        }
    }
    return Math.max(up, down);
}
