function maxProduct(n: number): number {
    let [a, b] = [0, 0];
    for (; n; n = Math.floor(n / 10)) {
        const x = n % 10;
        if (a < x) {
            [a, b] = [x, a];
        } else if (b < x) {
            b = x;
        }
    }
    return a * b;
}
