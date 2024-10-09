function minRectanglesToCoverPoints(points: number[][], w: number): number {
    points.sort((a, b) => a[0] - b[0]);
    let [ans, x1] = [0, -1];
    for (const [x, _] of points) {
        if (x > x1) {
            ++ans;
            x1 = x + w;
        }
    }
    return ans;
}
