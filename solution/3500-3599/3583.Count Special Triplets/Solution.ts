function specialTriplets(nums: number[]): number {
    const left = new Map<number, number>();
    const right = new Map<number, number>();
    for (const x of nums) {
        right.set(x, (right.get(x) || 0) + 1);
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (const x of nums) {
        right.set(x, (right.get(x) || 0) - 1);
        const lx = left.get(x * 2) || 0;
        const rx = right.get(x * 2) || 0;
        ans = (ans + ((lx * rx) % mod)) % mod;
        left.set(x, (left.get(x) || 0) + 1);
    }
    return ans;
}
