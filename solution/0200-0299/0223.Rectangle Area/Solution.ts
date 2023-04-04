function computeArea(
    ax1: number,
    ay1: number,
    ax2: number,
    ay2: number,
    bx1: number,
    by1: number,
    bx2: number,
    by2: number,
): number {
    const a = (ax2 - ax1) * (ay2 - ay1);
    const b = (bx2 - bx1) * (by2 - by1);
    const width = Math.min(ax2, bx2) - Math.max(ax1, bx1);
    const height = Math.min(ay2, by2) - Math.max(ay1, by1);
    return a + b - Math.max(width, 0) * Math.max(height, 0);
}
