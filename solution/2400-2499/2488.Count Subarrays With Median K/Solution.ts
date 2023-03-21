function countSubarrays(nums: number[], k: number): number {
    const i = nums.indexOf(k);
    const n = nums.length;
    const cnt = new Array((n << 1) | 1).fill(0);
    let ans = 1;
    let x = 0;
    for (let j = i + 1; j < n; ++j) {
        x += nums[j] > k ? 1 : -1;
        ans += x >= 0 && x <= 1 ? 1 : 0;
        ++cnt[x + n];
    }
    x = 0;
    for (let j = i - 1; ~j; --j) {
        x += nums[j] > k ? 1 : -1;
        ans += x >= 0 && x <= 1 ? 1 : 0;
        ans += cnt[-x + n] + cnt[-x + 1 + n];
    }
    return ans;
}
