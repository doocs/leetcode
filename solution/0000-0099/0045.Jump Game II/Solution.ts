function jump(nums: number[]): number {
    let ans = 0;
    let mx = 0;
    let last = 0;
    for (let i = 0; i < nums.length - 1; ++i) {
        mx = Math.max(mx, i + nums[i]);
        if (last === i) {
            ++ans;
            last = mx;
        }
    }
    return ans;
}
