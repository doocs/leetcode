function getMaxLen(nums: number[]): number {
    const n = nums.length;
    let [f, g] = [0, 0];
    if (nums[0] > 0) {
        f = 1;
    } else if (nums[0] < 0) {
        g = 1;
    }
    let ans = f;
    for (let i = 1; i < n; i++) {
        let [ff, gg] = [0, 0];
        if (nums[i] > 0) {
            ff = f + 1;
            gg = g > 0 ? g + 1 : 0;
        } else if (nums[i] < 0) {
            ff = g > 0 ? g + 1 : 0;
            gg = f + 1;
        }
        [f, g] = [ff, gg];
        ans = Math.max(ans, f);
    }
    return ans;
}
