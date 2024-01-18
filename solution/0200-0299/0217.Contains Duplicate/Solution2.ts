function containsDuplicate(nums: number[]): boolean {
    return new Set<number>(nums).size !== nums.length;
}
