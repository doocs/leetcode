function minimumOperations(nums: number[]): number {
    const s = new Set(nums);
    s.delete(0);
    return s.size;
}
