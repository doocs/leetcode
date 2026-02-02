function maximumAND(nums: number[], k: number, m: number): number {
    const mx = 32 - Math.clz32(Math.max(...nums) + k);

    let ans = 0;
    const n = nums.length;
    const cost = new Array(n);

    for (let bit = mx - 1; bit >= 0; bit--) {
        let target = ans | (1 << bit);
        for (let i = 0; i < n; i++) {
            const x = nums[i];
            const diff = target & ~x;
            const j = diff === 0 ? 0 : 32 - Math.clz32(diff);
            const mask = (1 << j) - 1;
            cost[i] = (target & mask) - (x & mask);
        }
        cost.sort((a, b) => a - b);
        let sum = 0;
        for (let i = 0; i < m; i++) {
            sum += cost[i];
        }
        if (sum <= k) {
            ans = target;
        }
    }

    return ans;
}
