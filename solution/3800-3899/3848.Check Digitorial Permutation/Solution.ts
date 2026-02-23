function isDigitorialPermutation(n: number): boolean {
    const f: number[] = new Array(10);
    f[0] = 1;
    for (let i = 1; i < 10; i++) {
        f[i] = f[i - 1] * i;
    }

    let x = 0;
    let y = n;

    while (y > 0) {
        x += f[y % 10];
        y = Math.floor(y / 10);
    }

    const a = x.toString().split('').sort().join('');
    const b = n.toString().split('').sort().join('');

    return a === b;
}
