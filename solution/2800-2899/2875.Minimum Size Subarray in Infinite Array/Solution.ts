function minSizeSubarray(nums: number[], target: number): number {
    const s = nums.reduce((a, b) => a + b);
    const n = nums.length;
    let a = 0;
    if (target > s) {
        a = n * ((target / s) | 0);
        target -= ((target / s) | 0) * s;
    }
    if (target === s) {
        return n;
    }
    const pos: Map<number, number> = new Map();
    let pre = 0;
    pos.set(0, -1);
    let b = Infinity;
    for (let i = 0; i < n; ++i) {
        pre += nums[i];
        if (pos.has(pre - target)) {
            b = Math.min(b, i - pos.get(pre - target)!);
        }
        if (pos.has(pre - (s - target))) {
            b = Math.min(b, n - (i - pos.get(pre - (s - target))!));
        }
        pos.set(pre, i);
    }
    return b === Infinity ? -1 : a + b;
}
