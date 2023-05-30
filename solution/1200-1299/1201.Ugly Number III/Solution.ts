function nthUglyNumber(n: number, a: number, b: number, c: number): number {
    const ab = lcm(BigInt(a), BigInt(b));
    const bc = lcm(BigInt(b), BigInt(c));
    const ac = lcm(BigInt(a), BigInt(c));
    const abc = lcm(BigInt(a), bc);
    let l = 1n;
    let r = BigInt(2e9);
    while (l < r) {
        const mid = (l + r) >> 1n;
        const count =
            mid / BigInt(a) +
            mid / BigInt(b) +
            mid / BigInt(c) -
            mid / ab -
            mid / bc -
            mid / ac +
            mid / abc;
        if (count >= BigInt(n)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }
    return Number(l);
}

function gcd(a: bigint, b: bigint): bigint {
    return b === 0n ? a : gcd(b, a % b);
}

function lcm(a: bigint, b: bigint): bigint {
    return (a * b) / gcd(a, b);
}
