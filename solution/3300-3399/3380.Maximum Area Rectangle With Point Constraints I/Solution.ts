function maxRectangleArea(points: number[][]): number {
    const check = (x1: number, y1: number, x2: number, y2: number): boolean => {
        let cnt = 0;
        for (const point of points) {
            const [x, y] = point;
            if (x < x1 || x > x2 || y < y1 || y > y2) {
                continue;
            }
            if ((x === x1 || x === x2) && (y === y1 || y === y2)) {
                cnt++;
                continue;
            }
            return false;
        }
        return cnt === 4;
    };

    let ans = -1;
    for (let i = 0; i < points.length; i++) {
        const [x1, y1] = points[i];
        for (let j = 0; j < i; j++) {
            const [x2, y2] = points[j];
            const [x3, y3] = [Math.min(x1, x2), Math.min(y1, y2)];
            const [x4, y4] = [Math.max(x1, x2), Math.max(y1, y2)];
            if (check(x3, y3, x4, y4)) {
                ans = Math.max(ans, (x4 - x3) * (y4 - y3));
            }
        }
    }
    return ans;
}
