function categorizeBox(
    length: number,
    width: number,
    height: number,
    mass: number,
): string {
    const v = length * width * height;
    let i = 0;
    if (
        length >= 10000 ||
        width >= 10000 ||
        height >= 10000 ||
        v >= 1000000000
    ) {
        i |= 1;
    }
    if (mass >= 100) {
        i |= 2;
    }
    return ['Neither', 'Bulky', 'Heavy', 'Both'][i];
}
