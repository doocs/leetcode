function numberOfGoodSubarraySplits(nums: number[]): number {
    let ans = 1;
    let j = -1;
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (nums[i] === 0) {
            continue;
        }
        if (j > -1) {
            ans = (ans * (i - j)) % mod;
        }
        j = i;
    }
    return j === -1 ? 0 : ans;
}
