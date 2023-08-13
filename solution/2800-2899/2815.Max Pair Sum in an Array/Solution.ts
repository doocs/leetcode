function maxSum(nums: number[]): number {
    const n = nums.length;
    let ans = -1;
    const f = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = Math.max(y, x % 10);
        }
        return y;
    };
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            const v = nums[i] + nums[j];
            if (ans < v && f(nums[i]) === f(nums[j])) {
                ans = v;
            }
        }
    }
    return ans;
}
