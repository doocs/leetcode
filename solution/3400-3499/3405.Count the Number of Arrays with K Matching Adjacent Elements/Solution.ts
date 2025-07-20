const MX = 1e5 + 10;
const MOD = BigInt(1e9 + 7);

const f: bigint[] = Array(MX).fill(1n);
const g: bigint[] = Array(MX).fill(1n);

function qpow(a: bigint, k: number): bigint {
    let res = 1n;
    while (k !== 0) {
        if ((k & 1) === 1) {
            res = (res * a) % MOD;
        }
        a = (a * a) % MOD;
        k >>= 1;
    }
    return res;
}

(function init() {
    for (let i = 1; i < MX; ++i) {
        f[i] = (f[i - 1] * BigInt(i)) % MOD;
        g[i] = qpow(f[i], Number(MOD - 2n));
    }
})();

function comb(m: number, n: number): bigint {
    return (((f[m] * g[n]) % MOD) * g[m - n]) % MOD;
}

export function countGoodArrays(n: number, m: number, k: number): number {
    const ans = (((comb(n - 1, k) * BigInt(m)) % MOD) * qpow(BigInt(m - 1), n - k - 1)) % MOD;
    return Number(ans);
}
