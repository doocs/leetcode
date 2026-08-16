function kthDigit(k: number): number {
    if (k <= 9) {
        return k;
    }

    k -= 9;
    let d = 2;
    let start = 1;
    let size = 0;

    while (true) {
        const cnt = 9 * Math.pow(10, d - 2);
        size = 10 * d;

        if (k <= cnt * size) {
            break;
        }

        k -= cnt * size;
        d++;
        start *= 10;
    }

    const b = start + Math.floor((k - 1) / size);
    const pos = (k - 1) % size;

    const i = Math.floor(pos / d);

    let num: number;
    if (b % 2 === 0) {
        num = 10 * b + i;
    } else {
        num = 10 * b + 9 - i;
    }

    return Number(String(num)[pos % d]);
}
