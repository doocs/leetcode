const MX = 500001;
const MOD = 1000000007n;

const f: bigint[] = new Array(MX).fill(1n);
const g: bigint[] = new Array(MX).fill(1n);

function pow(a: bigint, b: bigint): bigint {
    let res = 1n;
    while (b > 0n) {
        if (b & 1n) {
            res = (res * a) % MOD;
        }
        a = (a * a) % MOD;
        b >>= 1n;
    }
    return res;
}

for (let i = 1; i < MX; i++) {
    f[i] = (f[i - 1] * BigInt(i)) % MOD;
    g[i] = pow(f[i], MOD - 2n);
}

function comb(n: number, k: number): bigint {
    return (((f[n] * g[k]) % MOD) * g[n - k]) % MOD;
}

function countValidSequences(n: number, k: number): number {
    let ans = comb(n - 1, k - 1);

    if ((n + k) % 2 === 0) {
        ans = (ans - comb((n + k) / 2 - 1, k - 1) + MOD) % MOD;
    }

    return Number(ans);
}
