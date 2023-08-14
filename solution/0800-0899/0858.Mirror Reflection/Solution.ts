function mirrorReflection(p: number, q: number): number {
    const g = gcd(p, q);
    p = Math.floor(p / g) % 2;
    q = Math.floor(q / g) % 2;
    if (p === 1 && q === 1) {
        return 1;
    }
    return p === 1 ? 0 : 2;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
