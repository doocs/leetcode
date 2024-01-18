function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (Math.abs(i - j) <= k && nums[j] === key) {
                ans.push(i);
                break;
            }
        }
    }
    return ans;
}
