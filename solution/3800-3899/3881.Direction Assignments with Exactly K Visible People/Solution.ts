const N = 100001;
const MOD = 1000000007n;

const f: bigint[] = Array(N).fill(0n);
const g: bigint[] = Array(N).fill(0n);

function qmi(a: bigint, k: bigint, p: bigint): bigint {
    let res = 1n;
    while (k > 0n) {
        if (k & 1n) res = (res * a) % p;
        k >>= 1n;
        a = (a * a) % p;
    }
    return res;
}

f[0] = 1n;
g[0] = 1n;
for (let i = 1; i < N; i++) {
    f[i] = (f[i - 1] * BigInt(i)) % MOD;
    g[i] = qmi(f[i], MOD - 2n, MOD);
}

function comb(n: number, k: number): bigint {
    return (((f[n] * g[k]) % MOD) * g[n - k]) % MOD;
}

function countVisiblePeople(n: number, pos: number, k: number): number {
    const l = pos,
        r = n - pos - 1;
    let ans = 0n;

    for (let a = 0; a <= Math.min(k, l); a++) {
        const b = k - a;
        if (b <= r) {
            ans = (ans + ((((2n * comb(l, a)) % MOD) * comb(r, b)) % MOD)) % MOD;
        }
    }

    return Number(ans);
}
