function countLatticePoints(circles: number[][]): number {
    const n = circles.length;
    let minX = Number.MAX_SAFE_INTEGER,
        minY = minX,
        maxX = Number.MIN_SAFE_INTEGER,
        maxY = maxX;
    let squares = [];
    for (let [x, y, r] of circles) {
        minX = Math.min(x - r, minX);
        minY = Math.min(y - r, minY);
        maxX = Math.max(x + r, maxX);
        maxY = Math.max(y + r, maxY);
        squares.push(r ** 2);
    }
    let ans = 0;
    for (let i = minX; i <= maxX; i++) {
        for (let j = minY; j <= maxY; j++) {
            for (let k = 0; k < n; k++) {
                const [x, y] = circles[k];
                if ((i - x) ** 2 + (j - y) ** 2 <= squares[k]) {
                    ans++;
                    break;
                }
            }
        }
    }
    return ans;
}
