function maxBalancedSubarray(nums: number[]): number {
    const d = new Map<bigint, number>();
    let ans = 0;
    let a = 0;
    let b = nums.length;
    d.set(BigInt(b), -1);
    for (let i = 0; i < nums.length; ++i) {
        a ^= nums[i];
        b += nums[i] % 2 === 0 ? 1 : -1;
        const key = (BigInt(a) << 32n) | BigInt(b);
        if (d.has(key)) {
            ans = Math.max(ans, i - d.get(key)!);
        } else {
            d.set(key, i);
        }
    }
    return ans;
}
