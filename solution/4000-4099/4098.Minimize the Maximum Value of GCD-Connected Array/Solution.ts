function minimumMaxValue(n: number, index: number, value: number): number {
    if (value === 1) {
        return -1;
    }

    let p = value;
    for (let x = 2; x * x <= value; x++) {
        if (value % x === 0) {
            p = x;
            break;
        }
    }

    if (value - p - (index - 1) * 2 <= 0) {
        return -1;
    }
    if (index === n - 1) {
        return value;
    }
    return value + p + (n - 2 - index) * 2;
}
