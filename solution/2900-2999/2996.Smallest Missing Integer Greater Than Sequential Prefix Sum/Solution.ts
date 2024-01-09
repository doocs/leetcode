function missingInteger(nums: number[]): number {
    let s = nums[0];
    for (let j = 1; j < nums.length && nums[j] === nums[j - 1] + 1; ++j) {
        s += nums[j];
    }
    const vis: Set<number> = new Set(nums);
    for (let x = s; ; ++x) {
        if (!vis.has(x)) {
            return x;
        }
    }
}
