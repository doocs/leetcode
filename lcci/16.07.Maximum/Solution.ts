function maximum(a: number, b: number): number {
    const k: number = Number(
        ((BigInt(a) - BigInt(b)) >> BigInt(63)) & BigInt(1),
    );
    return a * (k ^ 1) + b * k;
}
