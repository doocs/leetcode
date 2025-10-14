function getSneakyNumbers(nums: number[]): number[] {
    const n = nums.length - 2;
    let xx = nums[n] ^ nums[n + 1];
    for (let i = 0; i < n; ++i) {
        xx ^= i ^ nums[i];
    }
    const k = Math.clz32(xx & -xx) ^ 31;
    const ans = [0, 0];
    for (const x of nums) {
        ans[(x >> k) & 1] ^= x;
    }
    for (let i = 0; i < n; ++i) {
        ans[(i >> k) & 1] ^= i;
    }
    return ans;
}
