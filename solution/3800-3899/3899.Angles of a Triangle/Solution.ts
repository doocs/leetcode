function internalAngles(sides: number[]): number[] {
    sides.sort((a, b) => a - b);
    const [a, b, c] = sides;
    if (a + b <= c) {
        return [];
    }
    const A = (Math.acos((b * b + c * c - a * a) / (2 * b * c)) * 180) / Math.PI;
    const B = (Math.acos((a * a + c * c - b * b) / (2 * a * c)) * 180) / Math.PI;
    const C = 180 - A - B;
    return [A, B, C];
}
