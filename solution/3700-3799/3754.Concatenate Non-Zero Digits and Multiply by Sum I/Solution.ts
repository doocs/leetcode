function sumAndMultiply(n: number): number {
    let p = 1;
    let x = 0;
    let s = 0;

    while (n > 0) {
        const v = n % 10;
        s += v;
        if (v !== 0) {
            x += p * v;
            p *= 10;
        }
        n = Math.floor(n / 10);
    }

    return x * s;
}
