function numberOfGoodPartitions(nums: number[]): number {
    const qpow = (a: number, n: number, mod: number) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const last: Map<number, number> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        last.set(nums[i], i);
    }
    const mod = 1e9 + 7;
    let [j, k] = [-1, 0];
    for (let i = 0; i < n; ++i) {
        j = Math.max(j, last.get(nums[i])!);
        if (i === j) {
            ++k;
        }
    }
    return qpow(2, k - 1, mod);
}
