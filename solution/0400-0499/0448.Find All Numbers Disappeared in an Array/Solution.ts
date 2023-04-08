function findDisappearedNumbers(nums: number[]): number[] {
    const n = nums.length;
    for (const x of nums) {
        const i = Math.abs(x) - 1;
        if (nums[i] > 0) {
            nums[i] *= -1;
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (nums[i] > 0) {
            ans.push(i + 1);
        }
    }
    return ans;
}
