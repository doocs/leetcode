function cutSquares(square1: number[], square2: number[]): number[] {
    const x1 = square1[0] + square1[2] / 2;
    const y1 = square1[1] + square1[2] / 2;
    const x2 = square2[0] + square2[2] / 2;
    const y2 = square2[1] + square2[2] / 2;
    if (x1 === x2) {
        const y3 = Math.min(square1[1], square2[1]);
        const y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
        return [x1, y3, x2, y4];
    }
    const k = (y2 - y1) / (x2 - x1);
    const b = y1 - k * x1;
    if (Math.abs(k) > 1) {
        const y3 = Math.min(square1[1], square2[1]);
        const x3 = (y3 - b) / k;
        const y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
        const x4 = (y4 - b) / k;
        if (x3 > x4 || (x3 === x4 && y3 > y4)) {
            return [x4, y4, x3, y3];
        }
        return [x3, y3, x4, y4];
    } else {
        const x3 = Math.min(square1[0], square2[0]);
        const y3 = k * x3 + b;
        const x4 = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
        const y4 = k * x4 + b;
        return [x3, y3, x4, y4];
    }
}
