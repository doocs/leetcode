function largestTriangleArea(points: number[][]): number {
    let ans = 0;
    for (const [x1, y1] of points) {
        for (const [x2, y2] of points) {
            for (const [x3, y3] of points) {
                const u1 = x2 - x1,
                    v1 = y2 - y1;
                const u2 = x3 - x1,
                    v2 = y3 - y1;
                const t = Math.abs(u1 * v2 - u2 * v1) / 2;
                ans = Math.max(ans, t);
            }
        }
    }
    return ans;
}
