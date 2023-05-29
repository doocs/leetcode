function applyOperations(nums: number[]): number[] {
    const n = nums.length;
    for (let i = 0; i < n - 1; ++i) {
        if (nums[i] === nums[i + 1]) {
            nums[i] <<= 1;
            nums[i + 1] = 0;
        }
    }
    const ans: number[] = Array(n).fill(0);
    let i = 0;
    for (const x of nums) {
        if (x !== 0) {
            ans[i++] = x;
        }
    }
    return ans;
}
