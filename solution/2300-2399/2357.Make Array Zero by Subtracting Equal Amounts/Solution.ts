function minimumOperations(nums: number[]): number {
    const set = new Set(nums);
    set.delete(0);
    return set.size;
}
