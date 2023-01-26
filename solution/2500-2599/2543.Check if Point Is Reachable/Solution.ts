function isReachable(targetX: number, targetY: number): boolean {
    const x = gcd(targetX, targetY);
    return (x & (x - 1)) === 0;
}

function gcd(a: number, b: number): number {
    return b == 0 ? a : gcd(b, a % b);
}
