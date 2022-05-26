function pivotIndex(nums: number[]): number {
    let rightSum = nums.reduce((a, c) => a + c, 0),
        leftSum = 0;
    for (let i = 0; i < nums.length; i++) {
        rightSum -= nums[i];
        if (leftSum == rightSum) return i;
        leftSum += nums[i];
    }
    return -1;
}
