function mostFrequent(nums: number[], key: number): number {
    const cnt: number[] = new Array(1001).fill(0);
    let ans = 0;
    let mx = 0;
    for (let i = 0; i < nums.length - 1; ++i) {
        if (nums[i] === key) {
            if (mx < ++cnt[nums[i + 1]]) {
                mx = cnt[nums[i + 1]];
                ans = nums[i + 1];
            }
        }
    }
    return ans;
}
