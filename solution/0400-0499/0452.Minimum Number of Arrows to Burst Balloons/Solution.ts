function findMinArrowShots(points: number[][]): number {
    points.sort((a, b) => a[1] - b[1]);
    let ans = 0;
    let last = -Infinity;
    for (const [a, b] of points) {
        if (last < a) {
            ans++;
            last = b;
        }
    }
    return ans;
}
