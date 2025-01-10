function isRectangleOverlap(rec1: number[], rec2: number[]): boolean {
    const [x1, y1, x2, y2] = rec1;
    const [x3, y3, x4, y4] = rec2;
    return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1);
}
