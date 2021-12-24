function isPowerOfThree(n: number): boolean {
    while (n > 2) {
        if (n % 3) return false;
        n /= 3;
    }
    return n == 1;
}
