function checkGoodInteger(n: number): boolean {
    let s: number = 0;
    for (; n; n = Math.floor(n / 10)) {
        const x = n % 10;
        s += x * (x - 1);
    }
    return s >= 50;
}
