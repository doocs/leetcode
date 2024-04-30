function maxOperations(nums: number[]): number {
    const s = nums[0] + nums[1];
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i + 1 < n && nums[i] + nums[i + 1] === s; i += 2) {
        ++ans;
    }
    return ans;
}
