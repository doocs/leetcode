const MX = 100001;
const MOD = 1000000007n;

const pow10: bigint[] = Array(MX).fill(1n);
for (let i = 1; i < MX; i++) {
    pow10[i] = (pow10[i - 1] * 10n) % MOD;
}

function sumAndMultiply(s: string, queries: number[][]): number[] {
    const n = s.length;
    const sumD = Array<number>(n + 1).fill(0);
    const cntN0 = Array<number>(n + 1).fill(0);
    const p: bigint[] = Array(n + 1).fill(0n);

    for (let i = 1; i <= n; i++) {
        const d = s.charCodeAt(i - 1) - 48;
        sumD[i] = sumD[i - 1] + d;
        cntN0[i] = cntN0[i - 1] + (d > 0 ? 1 : 0);
        p[i] = d > 0 ? (p[i - 1] * 10n + BigInt(d)) % MOD : p[i - 1];
    }

    const ans: number[] = [];
    for (const [l, r] of queries) {
        const n0 = cntN0[r + 1] - cntN0[l];
        const sd = BigInt(sumD[r + 1] - sumD[l]);
        const x = (p[r + 1] - ((p[l] * pow10[n0]) % MOD) + MOD) % MOD;
        ans.push(Number((x * sd) % MOD));
    }
    return ans;
}
