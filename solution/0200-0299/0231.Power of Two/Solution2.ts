function isPowerOfTwo(n: number): boolean {
    return n > 0 && n === (n & -n);
}
