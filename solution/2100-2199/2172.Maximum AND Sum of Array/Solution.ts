function maximumANDSum(nums: number[], numSlots: number): number {
    const n = nums.length;
    const m = numSlots << 1;
    const f: number[] = new Array(1 << m).fill(0);
    for (let i = 0; i < 1 << m; ++i) {
        const cnt = i
            .toString(2)
            .split('')
            .filter(c => c === '1').length;
        if (cnt > n) {
            continue;
        }
        for (let j = 0; j < m; ++j) {
            if (((i >> j) & 1) === 1) {
                f[i] = Math.max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & ((j >> 1) + 1)));
            }
        }
    }
    return Math.max(...f);
}
