function hasAlternatingBits(n: number): boolean {
    n ^= (n >> 1);
    return (n & (n + 1)) === 0;
}
