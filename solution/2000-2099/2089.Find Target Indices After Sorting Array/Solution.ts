function targetIndices(nums: number[], target: number): number[] {
    nums.sort((a, b) => a - b);
    let ans = [];
    for (let i = 0; i < nums.length && nums[i] <= target; i++) {
        let cur = nums[i];
        if (cur == target) {
            ans.push(i);
        }
    }
    return ans;
}
