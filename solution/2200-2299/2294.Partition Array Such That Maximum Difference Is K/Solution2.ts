function partitionArray(nums: number[], k: number): number {
    if (k === 0) return new Set(nums).size;

    let c = 0;

    while (nums.length) {
        const max = Math.max(...nums) - k;
        nums = nums.filter(x => x < max);
        c++;
    }

    return c;
}
