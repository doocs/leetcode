function totalMoney(n: number): number {
    const k = (n / 7) | 0;
    const b = n % 7;
    const s1 = (((28 + 28 + 7 * (k - 1)) * k) / 2) | 0;
    const s2 = (((k + 1 + k + 1 + b - 1) * b) / 2) | 0;
    return s1 + s2;
}
