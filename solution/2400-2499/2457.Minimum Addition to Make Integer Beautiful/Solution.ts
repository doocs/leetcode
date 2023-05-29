function makeIntegerBeautiful(n: number, target: number): number {
    const f = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        return y;
    };

    let x = 0;
    while (f(n + x) > target) {
        let y = n + x;
        let p = 10;
        while (y % 10 === 0) {
            y = Math.floor(y / 10);
            p *= 10;
        }
        x = (Math.floor(y / 10) + 1) * p - n;
    }
    return x;
}
