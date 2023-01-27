function numberOfArithmeticSlices(nums: number[]): number {
    let ans = 0;
    let cnt = 0;
    let d = 3000;
    for (let i = 0; i < nums.length - 1; ++i) {
        const a = nums[i];
        const b = nums[i + 1];
        if (b - a == d) {
            ++cnt;
        } else {
            d = b - a;
            cnt = 0;
        }
        ans += cnt;
    }
    return ans;
}
