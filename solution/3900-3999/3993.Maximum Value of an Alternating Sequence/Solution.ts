function maximumValue(n: number, s: number, m: number): number {
    if (n === 1) {
        return s;
    }
    return s + Math.floor(n / 2) * (m - 1) + 1;
}
