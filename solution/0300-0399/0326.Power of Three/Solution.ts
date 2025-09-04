function isPowerOfThree(n: number): boolean {
    while (n > 2) {
        if (n % 3 !== 0) {
            return false;
        }
        n = Math.floor(n / 3);
    }
    return n === 1;
}
