function add(a: number, b: number): number {
    while (b) {
        const c = (a & b) << 1;
        a ^= b;
        b = c;
    }
    return a;
}
