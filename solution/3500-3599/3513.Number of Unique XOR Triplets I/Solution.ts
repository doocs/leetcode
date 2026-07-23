function uniqueXorTriplets(nums: number[]): number {
    const n = nums.length;
    if (n <= 2) {
        return n;
    }
    return 1 << (32 - Math.clz32(n));
}
