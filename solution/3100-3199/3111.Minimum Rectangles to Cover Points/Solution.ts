function minRectanglesToCoverPoints(points: number[][], w: number): number {
    points.sort((a, b) => a[0] - b[0]);
    let ans = 0;
    let x1 = -Infinity;
    for (const [x, _] of points) {
        if (x1 + w < x) {
            x1 = x;
            ++ans;
        }
    }
    return ans;
}
