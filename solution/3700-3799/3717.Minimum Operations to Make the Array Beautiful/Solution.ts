function minOperations(nums: number[]): number {
    let f = new Map<number, number>();
    f.set(nums[0], 0);

    for (let i = 1; i < nums.length; i++) {
        const x = nums[i];
        const g = new Map<number, number>();

        for (const [pre, s] of f.entries()) {
            let cur = Math.floor((x + pre - 1) / pre) * pre;
            while (cur <= 100) {
                const val = s + (cur - x);
                const old = g.get(cur);
                if (old === undefined || old > val) {
                    g.set(cur, val);
                }
                cur += pre;
            }
        }
        f = g;
    }

    return Math.min(...f.values());
}
