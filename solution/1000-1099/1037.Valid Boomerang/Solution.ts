function isBoomerang(points: number[][]): boolean {
    const [x1, y1] = points[0];
    const [x2, y2] = points[1];
    const [x3, y3] = points[2];
    return (x1 - x2) * (y2 - y3) !== (x2 - x3) * (y1 - y2);
}
