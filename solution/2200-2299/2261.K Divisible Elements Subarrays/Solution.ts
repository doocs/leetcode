function countDistinct(nums: number[], k: number, p: number): number {
    const s = new Set<bigint>();
    const [base1, base2] = [131, 13331];
    const [mod1, mod2] = [1000000007, 1000000009];
    for (let i = 0; i < nums.length; i++) {
        let [h1, h2, cnt] = [0, 0, 0];
        for (let j = i; j < nums.length; j++) {
            if (nums[j] % p === 0) {
                cnt++;
                if (cnt > k) {
                    break;
                }
            }
            h1 = (h1 * base1 + nums[j]) % mod1;
            h2 = (h2 * base2 + nums[j]) % mod2;
            s.add((BigInt(h1) << 32n) | BigInt(h2));
        }
    }
    return s.size;
}
