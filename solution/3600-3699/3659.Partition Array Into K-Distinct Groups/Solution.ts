function partitionArray(nums: number[], k: number): boolean {
    const n = nums.length;
    if (n % k) {
        return false;
    }
    const m = n / k;
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        if (++cnt[x] > m) {
            return false;
        }
    }
    return true;
}
