function minOperations(nums: number[], k: number): number {
    return nums.filter(x => x < k).length;
}
