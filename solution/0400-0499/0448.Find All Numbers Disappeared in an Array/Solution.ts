function findDisappearedNumbers(nums: number[]): number[] {
    for (let i = 0; i < nums.length; i++) {
        let idx = Math.abs(nums[i]) - 1;
        if (nums[idx] > 0) {
            nums[idx] *= -1;
        }
    }
    let ans = [];
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            ans.push(i + 1);
        }
    }
    return ans;
}
