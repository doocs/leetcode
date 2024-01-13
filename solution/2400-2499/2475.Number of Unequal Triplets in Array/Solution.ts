function unequalTriplets(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; i++) {
        for (let j = i + 1; j < n - 1; j++) {
            for (let k = j + 1; k < n; k++) {
                if (nums[i] !== nums[j] && nums[j] !== nums[k] && nums[i] !== nums[k]) {
                    ans++;
                }
            }
        }
    }
    return ans;
}
