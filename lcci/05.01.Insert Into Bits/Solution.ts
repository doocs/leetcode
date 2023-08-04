function insertBits(N: number, M: number, i: number, j: number): number {
    for (let k = i; k <= j; ++k) {
        N &= ~(1 << k);
    }
    return N | (M << i);
}
