function countDistinctIntegers(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        nums.push(Number([...(nums[i] + '')].reverse().join('')));
    }
    return new Set(nums).size;
}
