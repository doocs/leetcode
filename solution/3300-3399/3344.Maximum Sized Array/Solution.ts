const MX = 1330;
const f: bigint[] = Array(MX).fill(0n);
(() => {
    f[0] = 0n;
    for (let i = 1; i < MX; i++) {
        f[i] = f[i - 1] + BigInt(i);
        for (let j = 0; j < i; j++) {
            f[i] += BigInt(2) * BigInt(i | j);
        }
    }
})();

function maxSizedArray(s: number): number {
    let l = 1,
        r = MX;
    const target = BigInt(s);

    while (l < r) {
        const m = (l + r + 1) >> 1;
        if ((f[m - 1] * BigInt(m - 1) * BigInt(m)) / BigInt(2) <= target) {
            l = m;
        } else {
            r = m - 1;
        }
    }
    return l;
}
