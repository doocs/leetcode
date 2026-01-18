function alternatingXOR(nums: number[], target1: number, target2: number): number {
    const MOD = 1_000_000_007;
    const cnt1 = new Map<number, number>();
    const cnt2 = new Map<number, number>();
    cnt2.set(0, 1);

    let pre = 0;
    let ans = 0;

    for (const x of nums) {
        pre ^= x;
        const a = cnt2.get(pre ^ target1) ?? 0;
        const b = cnt1.get(pre ^ target2) ?? 0;
        ans = (a + b) % MOD;
        cnt1.set(pre, ((cnt1.get(pre) ?? 0) + a) % MOD);
        cnt2.set(pre, ((cnt2.get(pre) ?? 0) + b) % MOD);
    }

    return ans;
}
