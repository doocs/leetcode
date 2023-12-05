function stringShift(s: string, shift: number[][]): string {
    let x = 0;
    for (const [a, b] of shift) {
        x += a === 0 ? -b : b;
    }
    x %= s.length;
    if (x < 0) {
        x += s.length;
    }
    return s.slice(-x) + s.slice(0, -x);
}
