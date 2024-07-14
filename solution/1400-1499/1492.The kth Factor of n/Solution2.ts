function kthFactor(n: number, k: number): number {
    let i: number = 1;
    for (; i < n / i; ++i) {
        if (n % i === 0 && --k === 0) {
            return i;
        }
    }
    if (i * i !== n) {
        --i;
    }
    for (; i > 0; --i) {
        if (n % Math.floor(n / i) === 0 && --k === 0) {
            return Math.floor(n / i);
        }
    }
    return -1;
}
