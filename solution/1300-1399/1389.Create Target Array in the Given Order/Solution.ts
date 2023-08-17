function createTargetArray(nums: number[], index: number[]): number[] {
    const ans: number[] = [];
    for (let i = 0; i < nums.length; i++) {
        ans.splice(index[i], 0, nums[i]);
    }
    return ans;
}
