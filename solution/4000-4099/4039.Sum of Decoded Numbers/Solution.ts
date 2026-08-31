function sumDecoded(nums: number[]): number {
    const mod = 1000000007n;
    let ans = 0n;

    for (const v of nums) {
        const d = Math.floor(v / 10);
        const w = v % 10;

        const s = String(d);
        const x = BigInt(s.slice(0, w));
        const y = BigInt(s.slice(w));

        ans = (ans + pow(x, y, mod)) % mod;
    }

    return Number(ans);
}

function pow(x: bigint, y: bigint, mod: bigint): bigint {
    let res = 1n;

    while (y > 0n) {
        if (y & 1n) {
            res = (res * x) % mod;
        }
        x = (x * x) % mod;
        y >>= 1n;
    }

    return res;
}
