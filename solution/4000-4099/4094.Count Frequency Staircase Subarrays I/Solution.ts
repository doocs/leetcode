function countStaircase(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const orders = new Map<number, number>();
        const cnt = new Map<number, number>();
        let ok = 0;
        for (let j = i; j < n; ++j) {
            const x = nums[j];
            if (!orders.has(x)) {
                orders.set(x, orders.size + 1);
            }
            const count = cnt.set(x, (cnt.get(x) ?? 0) + 1).get(x)!;
            const order = orders.get(x)!;
            if (count > order) {
                break;
            }
            if (count === order && ++ok === cnt.size) {
                ++ans;
            }
        }
    }
    return ans;
}
