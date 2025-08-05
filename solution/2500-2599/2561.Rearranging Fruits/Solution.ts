function minCost(basket1: number[], basket2: number[]): number {
    const n = basket1.length;
    const cnt: Map<number, number> = new Map();
    for (let i = 0; i < n; i++) {
        cnt.set(basket1[i], (cnt.get(basket1[i]) || 0) + 1);
        cnt.set(basket2[i], (cnt.get(basket2[i]) || 0) - 1);
    }
    let mi = Number.MAX_SAFE_INTEGER;
    const nums: number[] = [];
    for (const [x, v] of cnt.entries()) {
        if (v % 2 !== 0) {
            return -1;
        }
        for (let i = 0; i < Math.abs(v) / 2; i++) {
            nums.push(x);
        }
        mi = Math.min(mi, x);
    }

    nums.sort((a, b) => a - b);
    const m = nums.length;
    let ans = 0;
    for (let i = 0; i < m / 2; i++) {
        ans += Math.min(nums[i], mi * 2);
    }
    return ans;
}
