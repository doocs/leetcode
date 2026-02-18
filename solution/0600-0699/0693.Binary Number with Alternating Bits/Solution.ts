function hasAlternatingBits(n: number): boolean {
    let prev = -1;

    while (n !== 0) {
        const curr = n & 1;
        if (prev === curr) {
            return false;
        }
        prev = curr;
        n >>= 1;
    }

    return true;
}
