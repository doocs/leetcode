function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0, j = 0; i < n; ++i) {
        while (j < i - k || (j < n && nums[j] !== key)) {
            ++j;
        }
        if (j < n && j <= i + k) {
            ans.push(i);
        }
    }
    return ans;
}
