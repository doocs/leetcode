const MX = 1e5;
const MOD: bigint = BigInt(1e9 + 7);
const fac: bigint[] = Array(MX + 1);

const init = (() => {
    fac[0] = 1n;
    for (let i = 1; i <= MX; ++i) {
        fac[i] = (fac[i - 1] * BigInt(i)) % MOD;
    }
    return 0;
})();

function qpow(a: bigint, n: number): bigint {
    let ans = 1n;
    for (; n > 0; n >>= 1) {
        if (n & 1) {
            ans = (ans * a) % MOD;
        }
        a = (a * a) % MOD;
    }
    return ans;
}

function numberOfSequence(n: number, sick: number[]): number {
    const m = sick.length;
    const nums: number[] = Array(m + 1);
    nums[0] = sick[0];
    nums[m] = n - sick[m - 1] - 1;
    for (let i = 1; i < m; i++) {
        nums[i] = sick[i] - sick[i - 1] - 1;
    }

    const s = nums.reduce((acc, x) => acc + x, 0);
    let ans = fac[s];
    for (let x of nums) {
        if (x > 0) {
            ans = (ans * qpow(fac[x], Number(MOD) - 2)) % MOD;
        }
    }
    for (let i = 1; i < nums.length - 1; ++i) {
        if (nums[i] > 1) {
            ans = (ans * qpow(2n, nums[i] - 1)) % MOD;
        }
    }
    return Number(ans);
}
