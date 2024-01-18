function longestConsecutive(nums: number[]): number {
    const n = nums.length;
    if (n < 2) {
        return n;
    }
    let ans = 1;
    let t = 1;
    nums.sort((a, b) => a - b);
    for (let i = 1; i < n; ++i) {
        if (nums[i] === nums[i - 1]) {
            continue;
        }
        if (nums[i] === nums[i - 1] + 1) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
}
