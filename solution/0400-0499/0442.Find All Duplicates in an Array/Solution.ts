function findDuplicates(nums: number[]): number[] {
    for (let i = 0; i < nums.length; i++) {
        while (nums[i] !== nums[nums[i] - 1]) {
            const temp = nums[i];
            nums[i] = nums[temp - 1];
            nums[temp - 1] = temp;
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] !== i + 1) {
            ans.push(nums[i]);
        }
    }
    return ans;
}
