function checkOverlap(
    radius: number,
    xCenter: number,
    yCenter: number,
    x1: number,
    y1: number,
    x2: number,
    y2: number,
): boolean {
    const f = (i: number, j: number, k: number) => {
        if (i <= k && k <= j) {
            return 0;
        }
        return k < i ? i - k : k - j;
    };
    const a = f(x1, x2, xCenter);
    const b = f(y1, y2, yCenter);
    return a * a + b * b <= radius * radius;
}
