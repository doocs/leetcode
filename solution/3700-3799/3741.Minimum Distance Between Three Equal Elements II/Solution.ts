function minimumDistance(nums: number[]): number {
    const n = nums.length;
    const g = new Map<number, number[]>();

    for (let i = 0; i < n; i++) {
        if (!g.has(nums[i])) {
            g.set(nums[i], []);
        }
        g.get(nums[i])!.push(i);
    }

    const inf = 1 << 30;
    let ans = inf;

    for (const ls of g.values()) {
        const m = ls.length;
        for (let h = 0; h < m - 2; h++) {
            const i = ls[h];
            const k = ls[h + 2];
            const t = (k - i) * 2;
            ans = Math.min(ans, t);
        }
    }

    return ans === inf ? -1 : ans;
}
